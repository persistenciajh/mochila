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
sum (sal.cantidad) salida
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
sum (idi.cantidad) ingresos
from inv_producto ip  
left join  inv_detalle_ingreso idi on idi.secuencia_producto=ip.secuencia
group by ip.secuencia,ip.codigo, ip.nombre,ip.secuencia_tipo_producto
order by ip.secuencia
;
-- entradas menos salidas [STOCK] existencia
CREATE VIEW vinv_producto_existencia AS
select 
ip.secuencia,
ip.secuencia_tipo_producto,
ip.descripcion,
ip.edad_consumidor,
ip.genero_consumidor,
ip.ult_precio_compra,
ip.precio_venta,
idi.ingresos,
sal.salida,
(idi.ingresos-sal.salida) existencia
from inv_producto ip 
left join  vinv_producto_entrada idi on idi.secuencia=ip.secuencia
left join  vinv_producto_salida sal on sal.secuencia=ip.secuencia

--solo referencias
CREATE VIEW vinv_producto_existencia AS
select 
ip.secuencia secuencia_producto,
ip.secuencia_tipo_producto,
idi.ingresos,
sal.salida,
(idi.ingresos-sal.salida) existencia
from inv_producto ip 
left join  vinv_producto_entrada idi on idi.secuencia=ip.secuencia
left join  vinv_producto_salida sal on sal.secuencia=ip.secuencia

