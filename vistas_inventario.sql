select 
distinct 
ip.secuencia,
ip.codigo,
ip.nombre,
ip.secuencia_tipo_producto,
--sum (idi.cantidad) ingresos,
sum (sal.cantidad) salida
from inv_producto ip  

left join  inv_detalle_salida sal on sal.secuencia_producto=ip.secuencia
--left join  inv_detalle_ingreso idi on idi.secuencia_producto=ip.secuencia
group by ip.secuencia,ip.codigo, ip.nombre,ip.secuencia_tipo_producto
;

--salidas de mercancia 
CREATE VIEW vinv_producto_salida AS
select 
distinct 
ip.secuencia,
ip.codigo,
ip.nombre,
ip.secuencia_tipo_producto,
coalesce(sum(sal.cantidad),0) AS salida
from inv_producto ip  

left join  inv_detalle_salida sal on sal.secuencia_producto=ip.secuencia
group by ip.secuencia,ip.codigo, ip.nombre,ip.secuencia_tipo_producto
;

--entradas de mercancia
CREATE VIEW vinv_producto_entrada AS
select 
ip.secuencia,
ip.codigo,
ip.nombre,
ip.secuencia_tipo_producto,
coalesce(sum (idi.cantidad),0) AS ingresos
from inv_producto ip  
left join  inv_detalle_ingreso idi on idi.secuencia_producto=ip.secuencia
group by ip.secuencia,ip.codigo, ip.nombre,ip.secuencia_tipo_producto
order by ip.secuencia
;
-- entradas menos salidas [STOCK] existencia


--solo referencias
CREATE OR REPLACE VIEW vinv_producto_existencia AS 
SELECT ip.secuencia AS secuencia_producto,
    ip.secuencia_tipo_producto,
    coalesce(idi.ingresos,0) ingresos ,
    coalesce(sal.salida,0) salida ,
    coalesce(idi.ingresos,0) - coalesce(sal.salida,0) AS existencia
   FROM inv_producto ip
     LEFT JOIN vinv_producto_entrada idi ON idi.secuencia = ip.secuencia
     LEFT JOIN vinv_producto_salida sal ON sal.secuencia = ip.secuencia;



 -- factura
 SELECT DISTINCT 
sal.secuencia,
sal.codigo,
sal.secuencia_persona,
per.nombres,
dsal.secuencia_ingreso secuencia_salida,
ip.secuencia,
ip.codigo,
ip.nombre,
ip.secuencia_tipo_producto,
coalesce(sum(dsal.cantidad),0) AS cantidad
   FROM inv_producto ip
     LEFT JOIN inv_detalle_salida dsal ON dsal.secuencia_producto = ip.secuencia
     INNER JOIN inv_salida sal ON sal.secuencia= dsal.secuencia_ingreso
     LEFT JOIN gen_persona per ON per.secuencia= sal.secuencia_persona

  GROUP BY sal.secuencia,sal.codigo,sal.secuencia_persona,per.nombres,dsal.secuencia_ingreso,ip.secuencia, ip.codigo, ip.nombre, ip.secuencia_tipo_producto;


--Listado detallado de Productos
CREATE OR REPLACE VIEW rinv_detallado_producto AS 
select
pro.codigo,
vpe.existencia,
pro.nombre,
tpro.nombre tipo,
pro.descripcion,
pro.fecha_creacion,
us.nombre_usuario usuario_creacion,
pro.estado,
pro.precio_venta,
pro.ult_precio_compra,
pro.cantidad_min,
pro.descuento_max,
pro.edad_consumidor,
pro.genero_consumidor
from vinv_producto_existencia vpe
LEFT JOIN inv_producto pro ON pro.secuencia = vpe.secuencia_producto
LEFT JOIN inv_tipo_producto tpro ON tpro.secuencia = pro.secuencia_tipo_producto
LEFT JOIN gen_usuario us ON us.secuencia = pro.secuencia_usuario_creacion
order by pro.codigo