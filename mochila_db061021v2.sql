PGDMP         1    	        	    y            mochila    9.3.25    9.3.25 l    3           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            4           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            5           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            6           1262    16393    mochila    DATABASE     �   CREATE DATABASE mochila WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Colombia.1252' LC_CTYPE = 'Spanish_Colombia.1252';
    DROP DATABASE mochila;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            7           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    6            8           0    0    SCHEMA public    ACL     �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  postgres    false    6                        3079    11750    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            9           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    16395 	   con_pagos    TABLE     _  CREATE TABLE public.con_pagos (
    con_pagos bigint NOT NULL,
    account_id character varying(255),
    amount double precision,
    buyer_email character varying(255),
    buyer_full_name character varying(255),
    currency character varying(255),
    description_producto_servicio character varying(255),
    estado_pago character varying(255),
    fecha_creacion double precision,
    merchant_id character varying(255),
    reference_code character varying(255),
    signature character varying(255),
    tax double precision,
    tax_return_base double precision,
    test character varying(255)
);
    DROP TABLE public.con_pagos;
       public         postgres    false    6            �            1259    16403    contact    TABLE     �   CREATE TABLE public.contact (
    id integer NOT NULL,
    apellido character varying(255),
    ciudad character varying(255),
    nombre character varying(255),
    telefono character varying(255)
);
    DROP TABLE public.contact;
       public         postgres    false    6            �            1259    24636    gen_persona    TABLE     t  CREATE TABLE public.gen_persona (
    secuencia integer NOT NULL,
    apellidos character varying(255) NOT NULL,
    correo_electronico character varying(255) NOT NULL,
    descripcion character varying(255),
    direccion character varying(255),
    estado character varying(255),
    fecha_actualizacion timestamp without time zone,
    fecha_creacion timestamp without time zone NOT NULL,
    fecha_nacimiento timestamp without time zone,
    genero character varying(255),
    identificacion character varying(255) NOT NULL,
    nombres character varying(255) NOT NULL,
    telefono character varying(255),
    tipo_identificacion character varying(5),
    etiqueta_cliente character varying(5) DEFAULT 'N'::character varying,
    etiqueta_proveedor character varying(5) DEFAULT 'N'::character varying,
    etiqueta_vendedor character varying(5) DEFAULT 'N'::character varying
);
    DROP TABLE public.gen_persona;
       public         postgres    false    6            �            1259    24634    gen_persona_secuencia_seq    SEQUENCE     �   CREATE SEQUENCE public.gen_persona_secuencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.gen_persona_secuencia_seq;
       public       postgres    false    6    184            :           0    0    gen_persona_secuencia_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.gen_persona_secuencia_seq OWNED BY public.gen_persona.secuencia;
            public       postgres    false    183            �            1259    24679    gen_usuario    TABLE     �  CREATE TABLE public.gen_usuario (
    secuencia integer NOT NULL,
    clave character varying(255) NOT NULL,
    correo_electronico character varying(255) NOT NULL,
    descripcion character varying(255),
    estado character varying(255),
    fecha_actualizacion timestamp without time zone,
    fecha_creacion timestamp without time zone NOT NULL,
    secuencia_grupo bigint NOT NULL,
    nombre_usuario character varying(255) NOT NULL,
    secuencia_persona bigint
);
    DROP TABLE public.gen_usuario;
       public         postgres    false    6            �            1259    24677    gen_usuario_secuencia_seq    SEQUENCE     �   CREATE SEQUENCE public.gen_usuario_secuencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.gen_usuario_secuencia_seq;
       public       postgres    false    6    186            ;           0    0    gen_usuario_secuencia_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.gen_usuario_secuencia_seq OWNED BY public.gen_usuario.secuencia;
            public       postgres    false    185            �            1259    16479    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public       postgres    false    6            �            1259    24619    inv_detalle_ingreso    TABLE     �   CREATE TABLE public.inv_detalle_ingreso (
    secuencia integer NOT NULL,
    secuencia_ingreso bigint,
    secuencia_producto bigint,
    cantidad integer,
    precio double precision
);
 '   DROP TABLE public.inv_detalle_ingreso;
       public         postgres    false    6            �            1259    24617 !   inv_detalle_ingreso_secuencia_seq    SEQUENCE     �   CREATE SEQUENCE public.inv_detalle_ingreso_secuencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 8   DROP SEQUENCE public.inv_detalle_ingreso_secuencia_seq;
       public       postgres    false    6    182            <           0    0 !   inv_detalle_ingreso_secuencia_seq    SEQUENCE OWNED BY     g   ALTER SEQUENCE public.inv_detalle_ingreso_secuencia_seq OWNED BY public.inv_detalle_ingreso.secuencia;
            public       postgres    false    181            �            1259    24726    inv_detalle_salida    TABLE     �   CREATE TABLE public.inv_detalle_salida (
    secuencia integer NOT NULL,
    secuencia_ingreso bigint,
    secuencia_producto bigint,
    cantidad integer,
    precio double precision
);
 &   DROP TABLE public.inv_detalle_salida;
       public         postgres    false    6            �            1259    24724     inv_detalle_salida_secuencia_seq    SEQUENCE     �   CREATE SEQUENCE public.inv_detalle_salida_secuencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 7   DROP SEQUENCE public.inv_detalle_salida_secuencia_seq;
       public       postgres    false    6    190            =           0    0     inv_detalle_salida_secuencia_seq    SEQUENCE OWNED BY     e   ALTER SEQUENCE public.inv_detalle_salida_secuencia_seq OWNED BY public.inv_detalle_salida.secuencia;
            public       postgres    false    189            �            1259    16427    inv_foto_producto    TABLE     �  CREATE TABLE public.inv_foto_producto (
    secuencia bigint NOT NULL,
    descripcion character varying(255),
    estado character varying(255),
    fecha_actualizacion timestamp without time zone,
    fecha_creacion timestamp without time zone NOT NULL,
    img bytea,
    secuencia_producto bigint NOT NULL,
    secuencia_usuario_actualizacion bigint NOT NULL,
    secuencia_usuario_creacion bigint NOT NULL
);
 %   DROP TABLE public.inv_foto_producto;
       public         postgres    false    6            �            1259    24592    inv_ingreso    TABLE     n  CREATE TABLE public.inv_ingreso (
    secuencia integer NOT NULL,
    secuencia_persona bigint,
    secuencia_usuario bigint,
    codigo character varying(20),
    numero character varying(20),
    tipo character varying(10),
    total double precision,
    estado character varying(10),
    descripcion character varying(500),
    fecha timestamp with time zone
);
    DROP TABLE public.inv_ingreso;
       public         postgres    false    6            >           0    0    COLUMN inv_ingreso.secuencia    COMMENT     ?   COMMENT ON COLUMN public.inv_ingreso.secuencia IS 'secuencia';
            public       postgres    false    180            ?           0    0 $   COLUMN inv_ingreso.secuencia_persona    COMMENT     G   COMMENT ON COLUMN public.inv_ingreso.secuencia_persona IS 'proveedor';
            public       postgres    false    180            @           0    0    COLUMN inv_ingreso.codigo    COMMENT     T   COMMENT ON COLUMN public.inv_ingreso.codigo IS 'codigo que identifica el producto';
            public       postgres    false    180            A           0    0    COLUMN inv_ingreso.tipo    COMMENT     D   COMMENT ON COLUMN public.inv_ingreso.tipo IS 'tipo de comprobante';
            public       postgres    false    180            B           0    0    COLUMN inv_ingreso.total    COMMENT     K   COMMENT ON COLUMN public.inv_ingreso.total IS 'valor total del ingresol ';
            public       postgres    false    180            C           0    0    COLUMN inv_ingreso.fecha    COMMENT     C   COMMENT ON COLUMN public.inv_ingreso.fecha IS 'fecha del ingreso';
            public       postgres    false    180            �            1259    24590    inv_ingreso_secuencia_seq    SEQUENCE     �   CREATE SEQUENCE public.inv_ingreso_secuencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.inv_ingreso_secuencia_seq;
       public       postgres    false    6    180            D           0    0    inv_ingreso_secuencia_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.inv_ingreso_secuencia_seq OWNED BY public.inv_ingreso.secuencia;
            public       postgres    false    179            �            1259    24768    inv_producto    TABLE     �  CREATE TABLE public.inv_producto (
    secuencia integer NOT NULL,
    cantidad_min bigint,
    codigo character varying(255) NOT NULL,
    descripcion character varying(255),
    descuento_max bigint,
    edad_consumidor character varying(255),
    estado character varying(255),
    fecha_actualizacion timestamp without time zone,
    fecha_creacion timestamp without time zone,
    genero_consumidor character varying(255),
    nombre character varying(255) NOT NULL,
    precio_venta double precision,
    ult_precio_compra double precision,
    secuencia_tipo_producto bigint NOT NULL,
    secuencia_usuario_actualizacion bigint,
    secuencia_usuario_creacion bigint
);
     DROP TABLE public.inv_producto;
       public         postgres    false    6            �            1259    24766    inv_producto_secuencia_seq    SEQUENCE     �   CREATE SEQUENCE public.inv_producto_secuencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.inv_producto_secuencia_seq;
       public       postgres    false    6    194            E           0    0    inv_producto_secuencia_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.inv_producto_secuencia_seq OWNED BY public.inv_producto.secuencia;
            public       postgres    false    193            �            1259    24714 
   inv_salida    TABLE     �  CREATE TABLE public.inv_salida (
    secuencia integer NOT NULL,
    secuencia_persona bigint,
    secuencia_usuario bigint,
    codigo character varying(20),
    numero character varying(20),
    tipo character varying(10),
    total double precision,
    estado character varying(10),
    descripcion character varying(500),
    fecha timestamp with time zone,
    descuento bigint,
    impuesto bigint
);
    DROP TABLE public.inv_salida;
       public         postgres    false    6            F           0    0    COLUMN inv_salida.secuencia    COMMENT     >   COMMENT ON COLUMN public.inv_salida.secuencia IS 'secuencia';
            public       postgres    false    188            G           0    0 #   COLUMN inv_salida.secuencia_persona    COMMENT     D   COMMENT ON COLUMN public.inv_salida.secuencia_persona IS 'cliente';
            public       postgres    false    188            H           0    0    COLUMN inv_salida.codigo    COMMENT     S   COMMENT ON COLUMN public.inv_salida.codigo IS 'codigo que identifica el producto';
            public       postgres    false    188            I           0    0    COLUMN inv_salida.tipo    COMMENT     C   COMMENT ON COLUMN public.inv_salida.tipo IS 'tipo de comprobante';
            public       postgres    false    188            J           0    0    COLUMN inv_salida.total    COMMENT     J   COMMENT ON COLUMN public.inv_salida.total IS 'valor total del ingresol ';
            public       postgres    false    188            K           0    0    COLUMN inv_salida.fecha    COMMENT     B   COMMENT ON COLUMN public.inv_salida.fecha IS 'fecha del ingreso';
            public       postgres    false    188            L           0    0    COLUMN inv_salida.descuento    COMMENT     L   COMMENT ON COLUMN public.inv_salida.descuento IS 'porcentaje de descuento';
            public       postgres    false    188            M           0    0    COLUMN inv_salida.impuesto    COMMENT     I   COMMENT ON COLUMN public.inv_salida.impuesto IS 'impuesto iva colombia';
            public       postgres    false    188            �            1259    24712    inv_salida_secuencia_seq    SEQUENCE     �   CREATE SEQUENCE public.inv_salida_secuencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.inv_salida_secuencia_seq;
       public       postgres    false    6    188            N           0    0    inv_salida_secuencia_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.inv_salida_secuencia_seq OWNED BY public.inv_salida.secuencia;
            public       postgres    false    187            �            1259    24757    inv_tipo_producto    TABLE     �  CREATE TABLE public.inv_tipo_producto (
    secuencia integer NOT NULL,
    descripcion character varying(255),
    estado character varying(255),
    fecha_actualizacion timestamp without time zone,
    fecha_creacion timestamp without time zone,
    img bytea,
    nombre character varying(255) NOT NULL,
    secuencia_usuario_actualizacion bigint,
    secuencia_usuario_creacion bigint
);
 %   DROP TABLE public.inv_tipo_producto;
       public         postgres    false    6            �            1259    24755    inv_tipo_producto_secuencia_seq    SEQUENCE     �   CREATE SEQUENCE public.inv_tipo_producto_secuencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.inv_tipo_producto_secuencia_seq;
       public       postgres    false    192    6            O           0    0    inv_tipo_producto_secuencia_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.inv_tipo_producto_secuencia_seq OWNED BY public.inv_tipo_producto.secuencia;
            public       postgres    false    191            �            1259    16451    log    TABLE     �   CREATE TABLE public.log (
    id integer NOT NULL,
    date timestamp without time zone,
    details character varying(255),
    url character varying(255),
    username character varying(255)
);
    DROP TABLE public.log;
       public         postgres    false    6            �            1259    16459    persona    TABLE     ~  CREATE TABLE public.persona (
    prs_id bigint NOT NULL,
    prs_apellidos character varying(100) NOT NULL,
    prs_email character varying(100) NOT NULL,
    prs_genero character varying(100),
    prs_nombres character varying(100) NOT NULL,
    prs_nro_documento bigint NOT NULL,
    prs_idn_org character varying(100) NOT NULL,
    prs_usr_id character varying(100) NOT NULL
);
    DROP TABLE public.persona;
       public         postgres    false    6            �            1259    16467 	   user_role    TABLE     �   CREATE TABLE public.user_role (
    user_role_id integer NOT NULL,
    role character varying(45) NOT NULL,
    username character varying(45) NOT NULL
);
    DROP TABLE public.user_role;
       public         postgres    false    6            �            1259    16472    users    TABLE     �   CREATE TABLE public.users (
    username character varying(45) NOT NULL,
    clave character varying(60) NOT NULL,
    estado boolean NOT NULL
);
    DROP TABLE public.users;
       public         postgres    false    6            �            1259    24781    vinv_producto_entrada    VIEW     �  CREATE VIEW public.vinv_producto_entrada AS
 SELECT ip.secuencia,
    ip.codigo,
    ip.nombre,
    ip.secuencia_tipo_producto,
    sum(idi.cantidad) AS ingresos
   FROM (public.inv_producto ip
     LEFT JOIN public.inv_detalle_ingreso idi ON ((idi.secuencia_producto = ip.secuencia)))
  GROUP BY ip.secuencia, ip.codigo, ip.nombre, ip.secuencia_tipo_producto
  ORDER BY ip.secuencia;
 (   DROP VIEW public.vinv_producto_entrada;
       public       postgres    false    194    182    182    194    194    194    6            �            1259    24777    vinv_producto_salida    VIEW     n  CREATE VIEW public.vinv_producto_salida AS
 SELECT DISTINCT ip.secuencia,
    ip.codigo,
    ip.nombre,
    ip.secuencia_tipo_producto,
    sum(sal.cantidad) AS salida
   FROM (public.inv_producto ip
     LEFT JOIN public.inv_detalle_salida sal ON ((sal.secuencia_producto = ip.secuencia)))
  GROUP BY ip.secuencia, ip.codigo, ip.nombre, ip.secuencia_tipo_producto;
 '   DROP VIEW public.vinv_producto_salida;
       public       postgres    false    194    194    190    190    194    194    6            �            1259    24785    vinv_producto_existencia    VIEW     �  CREATE VIEW public.vinv_producto_existencia AS
 SELECT ip.secuencia AS secuencia_producto,
    ip.secuencia_tipo_producto,
    idi.ingresos,
    sal.salida,
    (idi.ingresos - sal.salida) AS existencia
   FROM ((public.inv_producto ip
     LEFT JOIN public.vinv_producto_entrada idi ON ((idi.secuencia = ip.secuencia)))
     LEFT JOIN public.vinv_producto_salida sal ON ((sal.secuencia = ip.secuencia)));
 +   DROP VIEW public.vinv_producto_existencia;
       public       postgres    false    194    194    195    195    196    196    6            �           2604    24639 	   secuencia    DEFAULT     ~   ALTER TABLE ONLY public.gen_persona ALTER COLUMN secuencia SET DEFAULT nextval('public.gen_persona_secuencia_seq'::regclass);
 D   ALTER TABLE public.gen_persona ALTER COLUMN secuencia DROP DEFAULT;
       public       postgres    false    184    183    184            �           2604    24682 	   secuencia    DEFAULT     ~   ALTER TABLE ONLY public.gen_usuario ALTER COLUMN secuencia SET DEFAULT nextval('public.gen_usuario_secuencia_seq'::regclass);
 D   ALTER TABLE public.gen_usuario ALTER COLUMN secuencia DROP DEFAULT;
       public       postgres    false    186    185    186                       2604    24622 	   secuencia    DEFAULT     �   ALTER TABLE ONLY public.inv_detalle_ingreso ALTER COLUMN secuencia SET DEFAULT nextval('public.inv_detalle_ingreso_secuencia_seq'::regclass);
 L   ALTER TABLE public.inv_detalle_ingreso ALTER COLUMN secuencia DROP DEFAULT;
       public       postgres    false    182    181    182            �           2604    24729 	   secuencia    DEFAULT     �   ALTER TABLE ONLY public.inv_detalle_salida ALTER COLUMN secuencia SET DEFAULT nextval('public.inv_detalle_salida_secuencia_seq'::regclass);
 K   ALTER TABLE public.inv_detalle_salida ALTER COLUMN secuencia DROP DEFAULT;
       public       postgres    false    190    189    190            ~           2604    24595 	   secuencia    DEFAULT     ~   ALTER TABLE ONLY public.inv_ingreso ALTER COLUMN secuencia SET DEFAULT nextval('public.inv_ingreso_secuencia_seq'::regclass);
 D   ALTER TABLE public.inv_ingreso ALTER COLUMN secuencia DROP DEFAULT;
       public       postgres    false    179    180    180            �           2604    24771 	   secuencia    DEFAULT     �   ALTER TABLE ONLY public.inv_producto ALTER COLUMN secuencia SET DEFAULT nextval('public.inv_producto_secuencia_seq'::regclass);
 E   ALTER TABLE public.inv_producto ALTER COLUMN secuencia DROP DEFAULT;
       public       postgres    false    194    193    194            �           2604    24717 	   secuencia    DEFAULT     |   ALTER TABLE ONLY public.inv_salida ALTER COLUMN secuencia SET DEFAULT nextval('public.inv_salida_secuencia_seq'::regclass);
 C   ALTER TABLE public.inv_salida ALTER COLUMN secuencia DROP DEFAULT;
       public       postgres    false    187    188    188            �           2604    24760 	   secuencia    DEFAULT     �   ALTER TABLE ONLY public.inv_tipo_producto ALTER COLUMN secuencia SET DEFAULT nextval('public.inv_tipo_producto_secuencia_seq'::regclass);
 J   ALTER TABLE public.inv_tipo_producto ALTER COLUMN secuencia DROP DEFAULT;
       public       postgres    false    191    192    192                      0    16395 	   con_pagos 
   TABLE DATA               �   COPY public.con_pagos (con_pagos, account_id, amount, buyer_email, buyer_full_name, currency, description_producto_servicio, estado_pago, fecha_creacion, merchant_id, reference_code, signature, tax, tax_return_base, test) FROM stdin;
    public       postgres    false    171   �                 0    16403    contact 
   TABLE DATA               I   COPY public.contact (id, apellido, ciudad, nombre, telefono) FROM stdin;
    public       postgres    false    172   ��       &          0    24636    gen_persona 
   TABLE DATA                 COPY public.gen_persona (secuencia, apellidos, correo_electronico, descripcion, direccion, estado, fecha_actualizacion, fecha_creacion, fecha_nacimiento, genero, identificacion, nombres, telefono, tipo_identificacion, etiqueta_cliente, etiqueta_proveedor, etiqueta_vendedor) FROM stdin;
    public       postgres    false    184   ��       P           0    0    gen_persona_secuencia_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.gen_persona_secuencia_seq', 2, true);
            public       postgres    false    183            (          0    24679    gen_usuario 
   TABLE DATA               �   COPY public.gen_usuario (secuencia, clave, correo_electronico, descripcion, estado, fecha_actualizacion, fecha_creacion, secuencia_grupo, nombre_usuario, secuencia_persona) FROM stdin;
    public       postgres    false    186   q�       Q           0    0    gen_usuario_secuencia_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.gen_usuario_secuencia_seq', 1, true);
            public       postgres    false    185            R           0    0    hibernate_sequence    SEQUENCE SET     C   SELECT pg_catalog.setval('public.hibernate_sequence', 4884, true);
            public       postgres    false    178            $          0    24619    inv_detalle_ingreso 
   TABLE DATA               q   COPY public.inv_detalle_ingreso (secuencia, secuencia_ingreso, secuencia_producto, cantidad, precio) FROM stdin;
    public       postgres    false    182   ׊       S           0    0 !   inv_detalle_ingreso_secuencia_seq    SEQUENCE SET     P   SELECT pg_catalog.setval('public.inv_detalle_ingreso_secuencia_seq', 10, true);
            public       postgres    false    181            ,          0    24726    inv_detalle_salida 
   TABLE DATA               p   COPY public.inv_detalle_salida (secuencia, secuencia_ingreso, secuencia_producto, cantidad, precio) FROM stdin;
    public       postgres    false    190   �       T           0    0     inv_detalle_salida_secuencia_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.inv_detalle_salida_secuencia_seq', 5, true);
            public       postgres    false    189                      0    16427    inv_foto_producto 
   TABLE DATA               �   COPY public.inv_foto_producto (secuencia, descripcion, estado, fecha_actualizacion, fecha_creacion, img, secuencia_producto, secuencia_usuario_actualizacion, secuencia_usuario_creacion) FROM stdin;
    public       postgres    false    173   C�       "          0    24592    inv_ingreso 
   TABLE DATA               �   COPY public.inv_ingreso (secuencia, secuencia_persona, secuencia_usuario, codigo, numero, tipo, total, estado, descripcion, fecha) FROM stdin;
    public       postgres    false    180   �       U           0    0    inv_ingreso_secuencia_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.inv_ingreso_secuencia_seq', 122, true);
            public       postgres    false    179            0          0    24768    inv_producto 
   TABLE DATA               3  COPY public.inv_producto (secuencia, cantidad_min, codigo, descripcion, descuento_max, edad_consumidor, estado, fecha_actualizacion, fecha_creacion, genero_consumidor, nombre, precio_venta, ult_precio_compra, secuencia_tipo_producto, secuencia_usuario_actualizacion, secuencia_usuario_creacion) FROM stdin;
    public       postgres    false    194   y�       V           0    0    inv_producto_secuencia_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.inv_producto_secuencia_seq', 2, true);
            public       postgres    false    193            *          0    24714 
   inv_salida 
   TABLE DATA               �   COPY public.inv_salida (secuencia, secuencia_persona, secuencia_usuario, codigo, numero, tipo, total, estado, descripcion, fecha, descuento, impuesto) FROM stdin;
    public       postgres    false    188   �       W           0    0    inv_salida_secuencia_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.inv_salida_secuencia_seq', 1, false);
            public       postgres    false    187            .          0    24757    inv_tipo_producto 
   TABLE DATA               �   COPY public.inv_tipo_producto (secuencia, descripcion, estado, fecha_actualizacion, fecha_creacion, img, nombre, secuencia_usuario_actualizacion, secuencia_usuario_creacion) FROM stdin;
    public       postgres    false    192   {�       X           0    0    inv_tipo_producto_secuencia_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.inv_tipo_producto_secuencia_seq', 3, true);
            public       postgres    false    191                      0    16451    log 
   TABLE DATA               ?   COPY public.log (id, date, details, url, username) FROM stdin;
    public       postgres    false    174   Ϳ                 0    16459    persona 
   TABLE DATA               �   COPY public.persona (prs_id, prs_apellidos, prs_email, prs_genero, prs_nombres, prs_nro_documento, prs_idn_org, prs_usr_id) FROM stdin;
    public       postgres    false    175   ��                0    16467 	   user_role 
   TABLE DATA               A   COPY public.user_role (user_role_id, role, username) FROM stdin;
    public       postgres    false    176   Փ                0    16472    users 
   TABLE DATA               8   COPY public.users (username, clave, estado) FROM stdin;
    public       postgres    false    177   �      �           2606    16402    con_pagos_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.con_pagos
    ADD CONSTRAINT con_pagos_pkey PRIMARY KEY (con_pagos);
 B   ALTER TABLE ONLY public.con_pagos DROP CONSTRAINT con_pagos_pkey;
       public         postgres    false    171    171            �           2606    16410    contact_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.contact
    ADD CONSTRAINT contact_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.contact DROP CONSTRAINT contact_pkey;
       public         postgres    false    172    172            �           2606    24644    gen_persona_pk 
   CONSTRAINT     _   ALTER TABLE ONLY public.gen_persona
    ADD CONSTRAINT gen_persona_pk PRIMARY KEY (secuencia);
 D   ALTER TABLE ONLY public.gen_persona DROP CONSTRAINT gen_persona_pk;
       public         postgres    false    184    184            �           2606    24687    gen_usuario_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.gen_usuario
    ADD CONSTRAINT gen_usuario_pkey PRIMARY KEY (secuencia);
 F   ALTER TABLE ONLY public.gen_usuario DROP CONSTRAINT gen_usuario_pkey;
       public         postgres    false    186    186            �           2606    24731    inv_detalle_inv_salida_pk 
   CONSTRAINT     q   ALTER TABLE ONLY public.inv_detalle_salida
    ADD CONSTRAINT inv_detalle_inv_salida_pk PRIMARY KEY (secuencia);
 V   ALTER TABLE ONLY public.inv_detalle_salida DROP CONSTRAINT inv_detalle_inv_salida_pk;
       public         postgres    false    190    190            �           2606    16434    inv_foto_producto_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY public.inv_foto_producto
    ADD CONSTRAINT inv_foto_producto_pkey PRIMARY KEY (secuencia);
 R   ALTER TABLE ONLY public.inv_foto_producto DROP CONSTRAINT inv_foto_producto_pkey;
       public         postgres    false    173    173            �           2606    24776    inv_producto_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.inv_producto
    ADD CONSTRAINT inv_producto_pkey PRIMARY KEY (secuencia);
 H   ALTER TABLE ONLY public.inv_producto DROP CONSTRAINT inv_producto_pkey;
       public         postgres    false    194    194            �           2606    24722    inv_salida_pk 
   CONSTRAINT     ]   ALTER TABLE ONLY public.inv_salida
    ADD CONSTRAINT inv_salida_pk PRIMARY KEY (secuencia);
 B   ALTER TABLE ONLY public.inv_salida DROP CONSTRAINT inv_salida_pk;
       public         postgres    false    188    188            �           2606    24765    inv_tipo_producto_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY public.inv_tipo_producto
    ADD CONSTRAINT inv_tipo_producto_pkey PRIMARY KEY (secuencia);
 R   ALTER TABLE ONLY public.inv_tipo_producto DROP CONSTRAINT inv_tipo_producto_pkey;
       public         postgres    false    192    192            �           2606    24600    llave primaria 
   CONSTRAINT     a   ALTER TABLE ONLY public.inv_ingreso
    ADD CONSTRAINT "llave primaria" PRIMARY KEY (secuencia);
 F   ALTER TABLE ONLY public.inv_ingreso DROP CONSTRAINT "llave primaria";
       public         postgres    false    180    180            �           2606    16458    log_pkey 
   CONSTRAINT     J   ALTER TABLE ONLY public.log
    ADD CONSTRAINT log_pkey PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.log DROP CONSTRAINT log_pkey;
       public         postgres    false    174    174            �           2606    16466    persona_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (prs_id);
 >   ALTER TABLE ONLY public.persona DROP CONSTRAINT persona_pkey;
       public         postgres    false    175    175            �           2606    24624    pk 
   CONSTRAINT     [   ALTER TABLE ONLY public.inv_detalle_ingreso
    ADD CONSTRAINT pk PRIMARY KEY (secuencia);
 @   ALTER TABLE ONLY public.inv_detalle_ingreso DROP CONSTRAINT pk;
       public         postgres    false    182    182            �           2606    16478    ukadnyt6agwl65jnnokuvnskhn2 
   CONSTRAINT     j   ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT ukadnyt6agwl65jnnokuvnskhn2 UNIQUE (role, username);
 O   ALTER TABLE ONLY public.user_role DROP CONSTRAINT ukadnyt6agwl65jnnokuvnskhn2;
       public         postgres    false    176    176    176            �           2606    16471    user_role_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (user_role_id);
 B   ALTER TABLE ONLY public.user_role DROP CONSTRAINT user_role_pkey;
       public         postgres    false    176    176            �           2606    16476 
   users_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (username);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public         postgres    false    177    177            �           2606    16526    fk2svos04wv92op6gs17m9omli1    FK CONSTRAINT     �   ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fk2svos04wv92op6gs17m9omli1 FOREIGN KEY (username) REFERENCES public.users(username);
 O   ALTER TABLE ONLY public.user_role DROP CONSTRAINT fk2svos04wv92op6gs17m9omli1;
       public       postgres    false    1944    176    177            �           2606    24688 	   personafk    FK CONSTRAINT     �   ALTER TABLE ONLY public.gen_usuario
    ADD CONSTRAINT personafk FOREIGN KEY (secuencia_persona) REFERENCES public.gen_persona(secuencia);
 ?   ALTER TABLE ONLY public.gen_usuario DROP CONSTRAINT personafk;
       public       postgres    false    186    184    1950               �   x���1nBA���\ �������$�Y�JH� *�O�������V�^@���x���7�������t�9�չ	�a��u��b%9&�L�5bUU78=�������&��mll-�t�9���M�V�R���
��2��آ�����?�ey rAR�            x������ � �      &   �   x���1�0���_�? y}-�0L�����(��H�&�{�u4&7\�.�O���׋������v!u�����b��J�H�H��K?gP�hMT�~�F�k2E�P#Ԃ��>��e9F�{�r��凭��w��a�|cWB�	f�6Y�M���KQU�)B<�cO$      (   V   x�3�44261���H-*���K�pH�M���K���,-.M,��W((*MMJ,�t�4202�5��52Q00�#�b038�b���� c��      $   1   x�3�4�4�44�42 .����44bNS((0���qqq l�
0      ,      x�3�44��4�4�42 �=... "z�            x��I�uIR��Y�`�蛚���D�0g�|��^��  t�fV��}�Ɖp77�&���/�����?���]����Η���W��!w�_�������g�N�n����o��ZJ9���ϟ�͕���j���ΥSH='�a��\+�����ե�{�������|j}y{����N��T[_g�]g]~�Zs�����s�q�9��pr�|r��G�];����y���
�9/�N3�'��J����9�6�M9���~��c,.�6{Kޱ��{t��Pb�a�}V��d5��3�ޱ�zw����\B�=�]��7�]�]ׯSZ����F��������-���C�V����ٯ�+����mc���n��~��0��sN۹�B!���
i���F��ͅ�\�!� ϸ56��~Ɨ�}�m�6��.��sKc�̏��ٮ��+6����=-�h�}y�:n>#�Y
F�G�Y-�!7�򊩏�c�����sXg�����!�9'�m���;�������&8�l��|B���)ۇv{[.�y�5`�Zļ�i�y�RK�2���Kr��M@��:���q+��{I���ݛ�d7�x���T.�jZ8AH���q�{�<[������k���u{��Jg�~yWؗ���wć���6�h:���e���`� ��l[̣�8]\4��t8ŭ�p��c�w�,���g�1�̜oIi��2�/��'��/�_��������N��c
�U,���e�;�L������,g�xzMd����y���=�=��֞U�ű��cH�C� ���Ŋ'ɾ����]����ku\��m.�R&�S{���;u�isP�s���Ak�����s��V���冫�������N�:�=s�H�4�8$'YW�![� ����������Uy�6V�zG�}j���s�;��X#G�+������](�;s� �C/6=rd�VY�g��u��#޵��'��@�3�aV���c+&iU�@�)���ƅ"[���f�ߗ-lm��,��u�{���W�]8d�L�M��.�������;v+�����w6��y�2K�����vB�i~���U���s��OO�*�m����|���Ιm-|��-�<A��P�@Q�j�@�;=�!V�4���ųS�N{��ۍg�x?$|�c�5A�D -x%kj�����ǈF?2���,�lL����7pk��J$���S���p��m���qs�S�����U8�M��O��Y��R�w�qD�Ğ�!�<+ۀOU����������@�*d��U�_�HޝW8�.��!t��M1�q?���9�=ūr�X�#�5ock��w����k����'��C|�`Ogb�9��D�Nq�#��,�՜�ɹL�{e���G<+-V`�����D��N�{�� 9�}���~�{�r@ ��z�+�kX�J�X_�"p������Y�]bM���>r<��pq�݈�'�F�'��7�Ca���'��6��9��8�Q0�$��5/t����V���6 A!d�x�ky��C���jW�x ��ɭ&4?=O��*@ǅaixx�ࢩb��	�ůXy��	�&�(Sq�"��������S���w�۫��`g�X���T� ���gΪo�����-,w�� �/\�3���٦ܓO�U����ZG�	��&W!�y�;"#��ᛟ�.�<�������ѶC�V<,��E�(<)a��� N)0O,=�� 馡�,'G�{m9�ɧ���j�D�A�*>5#g�_��S���\��`\�o�l�YT�� �#-��*�yK�<O�wA���%�"":�� ��h����)�U�l.Ƒ��8EC6W/^���"�B���n���IZ��u��$x�
6O(h4j"���B�>�\�}!�kr%`_CTM��U�(����
F�d��R���u�(�W��Ol@����R�1�s�p ��Qvoߴ@q�gV�%���`%^/P0º��g��{�,@���?�X��tR�M�ΜPV�e�
)j���1��Q�4q ΃=ů �|p��Ukb�:9b$��Bg����?�L�	kG���op
��÷�ǋ�U
�B�ǯnK�z��6��2��&jD��0�
g�U� f_ J�[ I�=�ȜΖwl�q� 7��"D�-5��%6y�0+���*�ҏ!����/f��}�]�E&%#�HG�6h
 �B:�$��l�%R�����T�0x�,����+n�V�DZ�����\�Á�p��bl�h\w���&�A����@ǉb�p�
6K�% �����p��g���	�[\�f9�y��;�ݲ�d���*�£ �h���&b�=��36o1�ր��~����K��	�CJ��T�ϐ�O�۫�+��ķ��	��q�)�'nN��,/:�C���>J��N4���!{�;BP P	�#��#��"����|�ۑ���<.R&�S&R�9]�7x��+昀(�.D�)KH��	������m�E��xWb�+�*���e���y#�� R���a�?��f��@C<�r@l�J���s��/�+��|�m��&L3�X�<3���E8�t�8����CL�|jHz����O9�M���' A�9��cs_�_���0��\��<��͞r�t&<���Z��C>p	�B���9��Ğ����_��a�&��z��(��b|a�aT��X�?ϫ� ��D7.VqmG�:�c�'���RA�_Eq��~�a������������3���D&d�4ɔ�7˅}����^�;�>*z��+��o�ٕ�� ���<Ʋduΰ;�+��Î���*��ٜc�܎�TB�C9��D��@"u0f�g%����`��� ��
x&��o N��[ޫGE �3�H{��/#�"�G�����;��H��Z��O����u�52�)����9� �l�8]X���<;�@��e����_�T-_$خ
�0�bA����X�M�ş�����\�|ź�v�h3Ư/ڏ��pe�x0j�����-�O�.�"�CaQxe�}� ���#b����M�+�HPF�?R�����g���x�v Q
����lDԡ9�r��Ò���8�s��%�R4"�BT�OLOOֱ� (ɬD�{�Ԡ.Oc)�F��MшP�9��>��x
t�,M�b®FEP�	%�@dީd��I�7%S�'ź+��QW����Č�30Z4gE��mq֭���hј-7���je�\+�X9+��t�m;bK9(6\�ׂ�J�ΐ��D"��!��x�t�N/င 5�K)��S�G���ϵ}�hsB�mz�@��w��X�G�XH�����Nf��HB3��yŮ�l�^��V��l6L*�l�9(\V9Pɇ�Ȫ|�B�R��@ez��d#�"�@�a����n:Jn����}VZp,h%hw��"m��z(3�%�Q�R���@��+�t�(%YU�׳(�*N�%^!K�H��.����-���v�84��D�~;��3�J���'w������|����U>�,C��@i!I)N<mp���T���rz-���"�"�+��������l��{ iae��&v�11.�pyV7�5��Σ5Įb��/�dR
��E�>C��]�ı~
�o������#�*��C���7Gl�/�Tl ��&f(��8�L�]ҵ�|!TC�q7螲����(��� ���+�.��)*/�"�Ԏ`V	�
Q���TSR�Q�0��%)ӫ|�Rf�2���兪���"�(0�K���"Ǉ�l㄃
�a���6p��'`>��Kc�(p\��*$��	�gEU � V,�[��?,]��,[=彖�ur(|p ���a#d
`�tSZ�vlpA�H!gv7q��X34U  +Ԉ�?�3�# ���OJ5�{���N�N�#���)����I�l�Zb�_�#�U�6��
Ѡ���ȸPIC?��"��b����<�^�\m)+#r%�	D0��q���?�pEP�gSF�bd    �=�W�')�3�I:��bY��_.��g�1�<%�����-X4WVM�pg�JѿʼD8	�F��vZ�����e`�z�\;P0E���7,��	ۊ,0'�> �iJ]s8��+<ܖ�r�qo����WԲ�f�ЭE	e�/���
�*k��)�����:@?>F�C%Y�P�^*�r�@��^c�J��3�&��p.��(V����դ."�IQ �Ir�J�Y!����i���T���I@y5Ê-#���=�q|3��Q.s�R�KA�V�L�+$�H�AKej,+���6�Y�Jª�Mk��~�l�*g<m�^Q�09��D�!u:hw�H�� ր�&���%_�2y�r�:X1JF$%*�!���z$�R���ʶ���W}+G�VZ�Eb���[H��� ���h����η������"��m��jNS�IXeGeE84ԫj�J��t�C~�8���^���ɒ��W�]����/��S�(�5��d/c<ƞp�0��K�%(�Z^\�$O����r�*ޢ!��1��>��
�j�����N�V�Z��i�C��w�:�ų\�s��}P"�#���<hg�T'�7BCU �P�^���%��ٷ �JP��</�҅�Z�R����_�r�J����& �T!1� $�L�B�-a�[}�z+A/��*��^�^��j�K����7�J�|�l"^�T���.���'�����=@/�:�dw�U|��ċz�%PHb��b�C1�m��N��_�[$�b�˘��Qm3�lim4��N�-"����i9����N��e���
��/f�
�,'P�����@HQ�z�&�}@4���/9���Y{p�Pn.��zd}�%���£3N�8-�U�$a��"d�J��Uz$djJ�c���`���B��@P�^a�Ew�햶B����V�4�4� "'�ܐG{y�<V�A�!��}�_U瘶�x;���W�͖�O4���M����j�w o��F��X�
>[],�J�CY�s��3ܛĽ%~_�q���@��C|�E]�S�V�+5Eȯ�n�Ŝ�5 �r@Y�� tD_A$vԜG�5X��z�R���ٯ�Lh��Z�����Y[uy���<�M�����gM�4���9P�۩��pp:1,�R��"Y2}��PWil��y���	���ОE�6���F��`�D��s��	��>v���
Fz���1��b��z������=�� �[ y�1A~�j(�Y���R!��0t�43���R��eW���85S ��R�k�W���{V��M��s��nV}*� ߸~�Q�a	*�S؎hYd�`T�t� �7X�gNK�_�P��N�v`�P���&Q��i�}��I�YiH�ȋ���q[�!䐥�GphUz\V�R�[0$HNA�g�m=�ܶȒ(Dίj��,h���nZ[%�����:&Ί� -y�nӭ
�g�`$�R}RrKVj�)0i��:6���t�������'���(KV�<u��РA���n�`9��?Dq"�Vg"t��cb9btQ��,f/��[}�B��$�<�d�|�Ց�����UdQ��2SI�(K2V�����,#\x8@�"���XOP�^�(x���U�P�
�?����w�M�bU2�)'gu^Ⱦ�r
����@�*)�,<�aMIą�U��T0�����#����r8�^V���W�+X���&�QTA����^�5:Y�vzy�|�;`�<��!V�� p0�&A� �B�(Uh�jD�ã�3і����5epI���kR,L��C�h-GU���c��rP��PuΨLX�MU�
�npX+��E�]ɳ���y�o��ѩO�5��J�#���X�E�G��e�L1��P��i$���*��!yU2.4��B��l�Q53Ĺ�jg�w�o�\:,��sj#��/1tTg�|������'����u/����Eyh�l(8<��:�$}`�D;`�`c�c�S	B<0rl����ϋ[�������r���S�� e 7�s��P��-���/c�0���@��B8d2h�V�W�ƫ�/Ŵ��(V e �L5#�
7JG5 �)���J*D%�Mvや�l�RIL�y��*�����S���P%x�\Y�a�~z���T�r��jWx�J��+�����O�s�����7�����	���@XS(C�轍S���u��6�"`���#�{�b!4R�
�x���e�e�Ę��Z~�ޡUMe9N@�EB�K��]Xp(v���dO�j+���pքA�P .��q<(�g�^ya0Kڡim��?�z�J�/w#AG�W�HW�g	s�R^*Z\Qub�V�J6��.]1��g��|W�ƭ�� �a@���vd �V����l�g�Lvp�o1/�C�Җ�=8�*�����U\z�݊2���������N ��TQ��+����:M�!�v�%Y˸2uM�f0 ���@ĥ�T�	�O\$J���Z��~u��μJ�2�|$P���rQ�^�C^YV�K��t�c8+BU����k�yNS�w}$B�u4W�����ӡ��*�m��28֨��RSX��#P({�E�K�B8&nZ�'o���ց1�uT�a��)KT�
{�@|���F�o��U4�vET�Z~̺� ����j��_cふ���b�ڠ�Q=Tq~u��Êءd#
�^�!z_UVVU��Q�Һ�}��adXul'Z�5��d%�Y�K�w�*ϋ���)�[F���c�pD3��ᴴ��2�]�p�c�*'�6_%�p#��T[��R�kJ�<1F� �����g��x�2ݠ�)�j�8Kb�2%d�Վ��G4�5�j��	D���'��J́s���o��x;�6���f���A���۞5T�Z�zm�2�ʰ�!�K�o��#�d�G��9��X߇r K��i��րR��M+)���%ʓ��v}��~�oS[���	ۘ�0�_�=�ݮ��$�]�xG!��N;�Z�{h4���\!
�&����j����մyl��~W��y���H0��V�[���&�s�X���y\�?�X;�#�dS��{��H�?X�o2a�}�9yYN����8���Ż*��J��Iz�u�rN	,仢��TUd��*w�9WQ�=*h?SՃt�{��F(0�W�4D�5Q�]6�$�S�]`<b�/��tJ��⳺�TB���K�~6�,��+�:�!+���#�	�3 ̼�ߟ̨���TT0B4�y��Vj�v�����B�1XY�)v�5��1c<�_�)��Uօ���`���ǻ
�'%�`�b��@�p��d�T���d�:xCv������G��b��xQX�>��՜������ ��t՛M��s1������s�Dū�\X�E]�m�"|·9=�:����{R�H�w�3��Q8���{U��Q?�r�#���[�_V�6fAs�4u�$U�OYX��F^��\?9Zu]�LI��O�ur����>�^à! ��yg��g�?p��%�:�dR��T!ĺq�Q���N��u`��i8�RJl�]H�E�
|jT��ZZ3��P%@�5���rK<u��‰��U<�H�@#�`�U��l9�h���d���CSW�@�@A��%X��f��O����p ���ȡ� Q�u/5���R`D�`�Q4:ltS�4y��GU��2a��ST'Ee[�)(寶)ۡ�WT�7!���"�V�8@W�01��E<`k"	3�}�z�*,��A߮Z/0��B_��7��&zְ��B,��\��7ס%�:�)?�,�%*a��PG״���S7����_��,A�p�2�l�����9���.�Dՙ�<���66°jf�eଊ��o�x��4��S5MDUJ|�W�}U��Ԯ��
;4Q��P�48�*�RX���pb���VI0�*�� �aϲ6h�
���)�v,����  g�F!�y}4 a#=K(�����*��9j8��|hajK�V��F��%Ş�Q�����5��W'��|�3!� 1��69q��l>�%��6�H�    j <] Ŧ��)�ԥ�Q,��8��������=��L8���u��EW�p_͚D]�~pm��/�<�.C��B� �ت�Ɩa�8��ދ�K ��U�5�fYt�Ǥ~=<R;iU�{k(��-]���=�5�V��"**���Jq��^vǻ$��vӹ���Jp|��Cz�n�������x��e�^Vjwbx���\�aĨf5�&��Y|��M/�)��Xo�h��{�ͅjSi�S�ՙP�:�\�D �6��q�ojQ��$��G�yk�/C��"��NU�s�;����S�M��
c�9��e�UU5��X���v.N����#5�qK�
JS5;�K+u��ǌ�K��*�9_��2�j���0��
Y���$�5ȩV�GY�%����4�2'IE;�J����ؔq����<���F��W���P^]e�&{���RDd�Q=��xC��CY�t��~�@�C�M?�E�!�lsʁY�Ԓ8��\��Pu��W6dj 1�5�yk`#a!�	��f�j���HJ��7��+|�J�ibQ�`*��K��m'o��pi4�	��p�&ɰ��3lۺ������D�A0؎�Ţn�NV"��pO��߶7��Zd[��:�X��7�� i?�:�����Ԭ��8�y�q�[�04�0��%q���n�}��S��Ӭ+��+�|q�%e�e�SmH񴡬"�QT�x�7�'_ج�����Jo�{߿JR���#�z�j7k0�M�Jfݣq?������Գ��r�Z_R���Ļ��V�)*>(���*�j?�5�K�q�`xX[�o�+�)���ج��?VQ6J)��A��`�|0 �2�h<rt^k�q�f���#�h5��Y�|�4�QQ��_������8}5-��e�P�l�|F����ѭd�R��������火��H�� 6�P�`6���E5��͔茿h���qԖj��hFQ������T�W"N�F7Y�Y����&c�:������'���xl��>HLPo��@� E�Ա��a�$ c�T �7B�SER�����ǯ�|����k*J�M�������4�79ԻJ��\�.]�v������h8���Om1�ԍ��u3�y_]/�@�Nݪ���Z�u$��E:-C�"P�U�T	DK���U�.�D��ބ�e��[�X1���āT�j�ouk��!˘F[B�g[�*��Ƨ�v
�����;�O"�U�|��q�X�zh����P9}5�g6��s�`��<�r�f責��'j'3�F$���IS��+��N!jվ�j���9��s -"B�P{��j�g���A��m:�����'phiC@+Ph�4*�5�2҃l~F��"֬� RD֟d�3��D��4n��Y����7��u:Ly�R����d?Hޮ>*5�Dˁ��z"�(x9�����l�w���h�%�-dC �g�21|�q0���k���a����՛�R�&�_u^�x{����W��e;��NU��zD(%�RЬ:��f��X1�HF��E�� � ]���&��6���o����PAȔ��D��m�U��S%��ojhQ瑶�	%�OB8��J���WXþߦ���v��U/�C!bn?,�ҵ
�m���xM���BUr��2�S��TJ�]T&Ah��{4���	�GiiG�$^��Y��	�
��_�1DY��.��j^�5����Uo�r)��"6DZ�FW���{����L���*�n5*�)h��S�6�QQ�����c����#����]RQ�b�*hN`I ȕTT�e*lX(A��c<�ɒ��FP���Ad��sTAi-xT@'�A��i6P��l4�k��
�*��\3�,�o����?E� �����J4.=��։�̦ZB���nKڕ��D�I�A5�s�\�fd��֞��ٍ�\W�`��&<�Z��� �����M¾.�cS��H��#�p�[�
�BP#�V����j�q|}D����	˔��WG4�F1T���Dݓ"k�<�^��F�<�o�WS�K�Ǭ��V���jV�F��P{����"��[ʪ�)KDW�7�џ�䦷鮣���+}ʋa���-WIV�M��B�Ҵ���]�W�ǪD�I�"#���f��x�f�lnhw��:`=�p��4�B/1| 5�F<��y�-�	j�{s�NK]��Ս*�M⠲���ğel�C�~��9��9�I� �=~
(Z�j�]��*�iub�j�(=D'�P����8a��-M�3�,̥tO0�sɱx�{���}mz�\Y\V��e�v쮮��\4	xlʹל�����������_]hP�!�>rT/ov�c�p5��`hN�+�4��:Z؟�eO6!�_����1�͋A�6��_��65�K�ټJ��f��{%$
:���DUS���]����8pѤ����p�ڝ[}���g��V�%��,�*���X��Ă���/�� �,�/�7���q��W�2�)�)��ן�,�&u4\�f|k��$�����#W��Z���b����C�2B��j	"���M�k���=��}]� [��t�A@���o�D7U(���Q,~��ԫw��㱁���n|��xzbm�m�t7Ɗ����!Fu���]�:�Z�%,�Jc��6m���#�[q�Z���j�'�i`l+禢��|e�J�b��X���٨O#u�ibS��v�_��(%�Iy�.5��W|���=I��r�}�s�j+T		�;ĹY%�԰��m]����ڔ���mY�rQ��;�f+`!W�������Q!+���U��H�i� �*�Br�q���{�9��,}�Ju�*ͻm��nKz���:g�:�U��ɜ��9Sh	����w���4�)5*n�
�@0,���Y��﮺�d�#���e���y�օw��F��h�A\�q�~��Yk�=��*���*�1Re�d����c�����۽:WW�Ӛ�E��UWT<�S��S�DX.;�g`�
WAl�������P�+�H��6�C h�Q�2�J�ZIm"=!���8�Q�E�j��-�I��0��aKڄ&&�ެn<��b�����,����<H9ͫh�Mf�z�?=�k$��IYFB7�D�K�Z��5��!h�YBQ���� u��"����@��ʬ�~��M�����"ҭ`���&��Ǯ�����[<�߲Zu*�S��-i��VQ��ɡ݅�z6�f	6��251��`����Zy�7������]�P�z�XSWy�J��b/Mުz�	/{OV�+=�̩Ov� "C˻9K��~x��{CcيN�D%�w�4U!�ʢ 
�uc����>КU7f]4.<�?K�Yq�_\V�F�w�E�)㌐�9���!z2�?؝�Ij��P�
?�{��e�M��F%,V��Y׭�u����Kߨ�O������z<Ϧ	� �T����I[p����{}ö�t�ۏ%N��Z5Y�:��r��F�}>n�u�u5�͈�>�yl1�t��uCܡ��w^�� ��X���Ly`'M�E�s�S������n��ܶ���=�T�|2��CpJ�Ǉ0Ӹ!�F��nI�Q��B��rT3���ļ�'�X}��ef�ͻ�H������lCє��԰+�D��T����N���Z`/������T�s]5EX=�C9@VO��mPZ��p�٘���o���2�>����fԒ҆��m�c���k'g�[�+�<�ݾ��?�����q��T������auN5jm\T>L�����Q�O�[X�O0͋�	I�Ch���P$����\B��$������](,Qm�0��oҽ��`��"9Gԭ26��9w/{h9I|�7;߇o)�C�;��Pݵ<�:�	�{��6�Ŋ���:`ma(�F�}��إ��p�kI����_��[�X�Z��+�(*�@�O��D���A��ݿ'w���;4)����O��UG��E�%$�ꢿ�bR��b �PWn��j0���ng���cA��'���N�u�a��ኚB�n��M�oߥʫ��uS`p�T�IC8�+	�U�g5�Z����^oc��&o5
�] �  ܠZ����V% �[==H�`�݀C��^B�us"����z&��~��
}S�(��QA�Duͩ�,�tP�O�ԟm�\GwA*k�h���8�¡m�Y��x��i���R�w��U�M�իC\�0�d���:-�]�̬�7���V5�ѣZ��^���7bGլ����$��ъ�c;
���."��:Y]h��*&K���T'3k��+��H��Yi���̄(�I�CfU{��Iuz]���x�S���g��YO�u���.�@�'��l�?�F�е�ݲq3ZBr��{W볏e���Cu�E���V%�z�5g��()��^S�ex����:�~�LS��-�����RQZx	�bs�[o�.�{�5�V����	�?����]��]����p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��p��į��n{�p�o����� ZC      "   f   x���1
�0��zs��@dg	�SI�Y1�Wy�sH�섿�/+]���9$��8�z�i�f�rn�_*�fk�Uk���&����Ȅ�CL��B���kp�ݥ:!9      0   �   x�}��
�@Eי��T�h�Kk����((���pZ> �E8�sZ��EK��C�a�rM:`�-��}q<�:�;v���S!���l����_�?% --��k`� �"��c���,�->�l�x��2��!��~ef9L��]k8�      *   O   x�34��4���443521 ��]Ad�#���k��g�c����cHh������������������rpp��qqq ���      .   B   x�3�v�	uQ�Tpr��q�r�t���4202�5��52W00�#�0L�i�eL�V�Z��=... C�v            x����7�-�{����@y)�1�'p�q����(�Fc��)���$����U����.�(�G�nN����,���5o����oƜl{2c�'�o�������˯��w���O_���������5����ݟ_���ǯ�����O��������_������O]�N��>���������?���������������?������o�������_?����>���|���?����O����?���|��8�Ӈ������?��ӻ�?|��YA�2�q ��??|��ӗ�����?�~y��������5����	�d��F����O~���3��\��"�~b\Ÿh�ǧ���O����¢�yF<�0#�Q�������×��7\'�F�Q���?�����������_����^�I^��P�qV�q��L���X~��˯� �Cc&F��_>}�������
kGF�l�Cu����ʌ��",Ǹ�ʸ��.B��+u@�A�E���%��:( �,�x�\P?vl�F\��⷏�۫����" FYp�XI7��ow[y������</�#9�$/̎fY�������Fk%M�xF:�Ȑ�$��6}=_���F��1ː$�$��k���d�
b!����u(�v�z�2j�9�	j!�<9;*s�Z��ƎVY�e!M*W� ��`v�'1y'(���e�n�ѓ��5-������NK�20�q�#��1�L8Aiz�#u�����N�d!gc/1B�91R�����S3���T"ؼ_��qE�o�^�S3���I�b�e��%R�1Vʓ'�� ����0%�AQ�1J��z����7f�<F�j$N�T�ZH��H���B����]��Q.�Cv����~`d�r��ұ
�FƐT��^�����H�{)�r���Ų�����a��J�RFv*��8�pҩb�E��?I�zұ��<Wڎ�1Z����\{PD�dL��X�*�J)3
CyrJ�Xb/b\�c�> eJ)Y���zа�N���#�d
(Ӵ=D����Q��!, ��c���O"<};����~@�C:Dფ�^7�����h��|r;H:"`[�E%� �NϜo��V�XhC��AeE��$�`���.�$I$��SV���{�[���6��() VH%L=J�  �=�a��B�I����Q�ĸ��QR��)��JT>J�o��Q���]�}�(�б�%)���qu��\R�c\���h��+�rҞ$`=V�qxϚ�1i:+`'�"��X�l@�#fX�1�
_�V����1�Z-)Z�X�@���i��pQ�!�k%������b��'ƠE�es}&)y
��@�Q�4�����F�c��JD�0~��:�]Ռ�SV��|u�*%�T0��r�$���6��B��]��Y+���1̋VL�5"Y1vS˖D8$3 ������Q͏��H�4�%�[Jt�LKI9��0�����Ξ�izJ��x'<��ć/_>}^D0P^���~9|eᘐئ�*�O�o[�D25�٬��hʨ���'��)O�x�W���eDp�L�l�!a<
����x�	e~/�镧bwjۓ뛱e�W�x'�eL��3�e�����e$�%�sgz�L��z�L�k&����6|�$<e$��bB��X/���J#�Kgzey���J"�����=XJV���8=|G�a��4���Sx�!N'$��4��d�s�!N�8 :P^_���8=<���x��鄀� s�L;��		�ݗKg�A���L��J<��;ٮ�(}b��!N�����8�PR��3qzF��MO4D�Cщ�(�O�m&Ǹg�}��x�c^�R6�ĳ�!^'����g�C���5�8��@���48�1z����>I>
�JV�| `'$�^�Q�@ݟ�6䡌�z>>x�3��{�L@�N (I��P�����$��B�pjGR��9}��je�yF�� ��5�{�~��������у�_���񝞃����7�声�	��T��a:1��L��sD06���߰���e��q�a�3"J�����3O��aڙ1]qoa�@�ɘ�|�^�	c�(�"�k����6#��������[Kf\�������7�UF�91��1�ײc�ȸ�EBj\��h��7)2�����^��k͐?��ļ{\K�� Z�!��w��4H�\J�GI�o}�GZRџ�Q�i�Y}-���$E��������$ŏ��0n�c�e���'5�a��y��沓$Q��Xʓ�#)h~��
	���@iƳ�x�
$nY�_$�f[���qd���m�@&F��V�a��y������@�e�PĴ�@�"#c/��Vb���iZ��j4�@�;E�0a�qU����ލ�K�+|�����)���60�=���%m`��GB)�䢇�&��F;Z:�T �����Z����&be%�n���u�**ء��/��'ި.X#�TLc�t�(?I�Ȋʵ�AI��$�r��ҊY#L�K����@�؝l�XʛE�sy�#� ��������2B,bw\��8a�h!�8���F|	�'�v���&g1g}0��0=���K`ޗ0VǮ�-��_�:5-������+ђ	�3�{
E!�DċV��&0�b��F.0\ 1��+��
&�����3L�)B��H�:0Nňb
��m�Ĉ�|�����lY�$+�k(Q�&e����������C��₋1+T"`��0qޮ;��71���QD^��f1c�C4�,:�q<�1D�ǃ�HOiN#h��bF@9�y�/�	�@9�si�����],�i0%���R^y(����YF��B��]���Ho�%8�e����+%���d\C�[t,���pdd�+�&X�-0��|��I�wK1߀AR�96�mM>���	�"e ##Q��R@`ۦ�l$�/	�P�A�ю�8 e\�okW��2C���q�C��!���-#U�?hhɒ`Pfc�6$z�d�����K�:$��q�hg1�)Յ��n�pr��'Fi��x��?�P���" ��,���" �$$���KB-5�L���'Z3�1�������!���3�����M���|�[.O�j��?�My�l��9K��1��m`t�kB�gI�_`�0��+��ོ�!��Ӄ�	ѣ]~A[˔1�G�¡�e��k��f9�9�j�kAn�8V=�g�r/�2�k$�b��rr�r��S֕P<�;~��V�(<h��]��r���K�%��E��b��� (}�/V{�Y�q��r7R*ݶ��|���\��g�6<�l�c�j<#�]��轔���sTE!��"ʼhG�E��KG_�<�E��SE��$���nw���)��9�ޡ�nB�V�wh��Q�F�z@�	�D�ȱC��W<j���c�Ft	'��e%����A/�]7H�f���7R�ӡo[B@yW��)xD{Sle�t`�aP:�i��^�EI�脫�,B�9�!RN�\��2;���Ha���i���/G����t������0�ߥ���qg��MM�(��� n�	%��N�Oǈ�
��3�O�kq.�3b�#XB�)}Hw�-p�J�8o����o�c^���\-D�R��FྖO�c	�6�g���q����x�;�%|���V-�X��8a�:p���8��U.�D���$�EY�>����7����Y���!wщ��k?d6	'e���'WN���u��7V�[�ei�t���2�|BD�X�W��&G�$s��W��Kw�|+�`�xѮ��W^��p2r�ɿr�\pQ��#f�+���D���+�τuj:�K8���O)0�y��I�! ��K����tC�`�/�+t^���:�uA�0�\�D�B匈qs�$��z"đQ��n/(���{�B�y��=`��š��R �(�";��TB�E}    S�H>�-pN���Q��jù�����t2c8��1�m���؃�H0�$ "���12ɩ�FB�3�L?
�����v P� ���1�,��=��S�`3�=h����kc4葄U��n���m<� ���� 0~@�S��'��@��9�;���vFo��!:&�FA:ŀ���a'�A� E��E�Ҷm��x�v!��Ni�'6A�E�$��i� ���UȽ�E�@��� �zPE=�����1g`��=i��r!����bu��v����だ��S��37�z�z�Ŭg��!!Qד��J�"��u t&:�u�� �?�%o�x��G �cF�H9�U�^O��MB�)�χ��w?~�b%�֚+��[�X�#�u�Ϙ��ǈ볎�����̴�&���wk։k�V	�Hy�v��i-".B-!,Q+L�V���gK�D��MK�NIDu��""È�r� DCy���\+�#ו��
�!`�| �x�u��-|�#N��]+�?5�>|<�{j�Ќ_�� "�����@6$X:���a�p�ར0l@i�G��q�����k�yBk�	��,�(� �5fb�����U1c��"<<p�H{��)�/.�FP$6������+(����I��
�,�AQ��y����?���I�)9+0��A,2����8"���|ߡ� �.C9'�����x�r���:A��h��v7M'褀f��h�qh:��<iEz�	�����ْ��Zxr�K����5?g �o\&�5A'4e�i+ڀ�RC������'�(όx���&"��DG 礟3R��8�)%���&��oED�IE��&��_����@Y�;
�ԙ�G�I�Pƣ$��Q�k,�理a�u"���)�;�	�������tD�uG��ezZ#p	+"蛁rB^F ��x��򥰋�	�'$��a1�h��6-�9S��}����0�Ca�^o>����#!1z�pk9"����eE7�2J�K�ʯ�\��<v�{Js2y"@h:̷�BR�Fh4�F�����
��z�W���6��^������N�PzҲ�fm�L.3��<�ԋ�.��NE=J�*���J[A����9�r���A�gd��ǩ�%7�T�8�$.�m�<K��~d��c��-<h�녔H��u��"���p=p���*n�gL�W\6��q�h�B�:qM>!�����(�Aa�4d�Vf�h��9��[�N�F�U�0Q�y�S�j`Ȟ[�#S_A`ƾ�,����g%GY쒸��)J����g���3����r�<l3��嗛<B7�뚑�Y��V�6�Ⱥ�@��+hN���b!쪤g(��,��匞v<�kM��ķR2�,��pb�/�Pb�h��Й�{Bd,�PNl��m\�0F��@ps̸-�����CP<O+�@���3J��䏧�-/2QX�>!��켃Vƀ�p1n�ka�qR^<f�����1�+�/L"&�p�a[�W��߰Ю�y_[��|C[�=��Wށ	�kZ�++�X� |��m8,��V �5�V��[A,�hs�r��[A,D\��EƷ�0�11��F�ķ��`��X�9�
� �)}`D�6 \��99ƕ�ۀ H FFi���$@Fø�v0�a�q1�ۀVH�(�K�blR!���d<FW0��0�4���ʌB! ���e�i]���dB�ұ�������H�S���
q��\���
4 t�|aQ�	e�G�R��r��s��L�2�|�=J�r��m5H|Cِ����z���)/[*�����1z�P�+_�'\�#�[y5.�#�m晝D�#�m��ǯ�����)�����w}�(5<|���S���4սbJD��c�����`x�z�^8��#�Q����<c�������:q��5)s�f`�9�Rk��8)]*t�>e	Ө��7�i; pC�Y�Cp��֌�qR^��J
�2"���2�m��Ŋ��������`�ώ<z�Bw��
n��.>��ȁ�q�:aȫ=#�kVJrӋ8uyiN��Lj��}'�J#"F�wh��w5�̢���T�og^��T�`h��B}�I�� ��ջ5>��kyĊi'C8�����I�"$;�	g���l;${�ٷg	�{�#.���8�͌X�T�R��h� (��7�`/P�\pfL�Ky@�e�e�h���`K�;8�_sZ.+s:)4���2�۳0Bŕ�&��B�2���
�@���R����ҙYsY�{�r;�kѹ`b�a�5q���!��6M��X%�"I'g��~�U������IpR�Ě���.H��i����qM �{D��6�eZFCy�o��k����fe�t\�2&�+rW�ܸ�F	�iqn��q��3�}(����ړ��22�H�km�t��i���
 ���1)$�i�	��0n1�LkQ����L�C2�u@00��7ɴ���hJ�:�'Lz������X��tF֡<�Z�$�tW	�=b��o�{!�G4z.8L���D�T-���X �MK)�6T�B\�h����P�01�H#�D@�ɘz��6��@�Eiʰ��-���Q��շ�
l4.8Z%�	� P�з$��f�-06�����5\<$T�;�6�F�!1 \�,i)y�p[�Z�<��}BCy�B>$��ɄCB��	�@��oo �g4��Qx�!�'L��~��@|w�~�\�X!F �K�}H@�d4��b}�4X�	x�9���rY�
P�?����o���%䄦�4(�جEC�Q6$$"�.�G�ўr	D 
 �B��<���q2_�-)���Ԅk�L9 no��d�f�|��,��%Ό��<�U^��@IP�ˉZ,E��J=X#0��������U1���Tޖ�P�Ih(�19u�M2�r�Z�P�� ���
K#33*��C�H	��l˹�At.���M���-��1#o�,g4���+M�b^�?/̢<�f��G�,��S�N&�J�&��I�x���	�_�<!�d�+�`e��'���A+Üo�T�y�OU��:�Hi^(*V�@&�C'���h�V-�6��.|� <����3Q6A��:�Cw���בp�CX�8����3ǎQ�������y�Y��R>�+��z3��e���� 2ưZx�{I*L�M8����'�[��!O@٥�>$����P.G�!tM�D��"�K��&NG�!�� �<��
6� q1����-o(`�>:It�Y��r����S��9�.���S�9�]F�#��.��!&'oY}���0����5Ar5c򔭎+�d-	���Z�1�M�hl>`��Q%�W �"i�E9�&m��]ӂ5'3Z�<��C�� �/H�q�u�"a�<.�Z�kOvh�򪸭֌�P�B��Xki��<�e�ŬY8c��Wl��tZʕ)�e�� M��gO�@R9��	��s ��K���+��g�I����>��θ\Kb�JB�6�ѓ��B~=פ�c�D(d�#��83#�x�ŸH�A�8�#���8	��t���'��=�bl�l1�Gk.�p-I�MOs�����7$��.$�nh�q����Et�Y/͒���b�9�0G��,� wJ�:J��� ����v���]�G>8���4 %�n>��H�� �����	m��g4���:��}BE���5��!�4�8�Ԏ��DCy_��x��i`$��eA�u��m&�����'�Q�A<XИ	�eĴ����".�I�+x�
"�|�jSCXACD4^c�m��5��D�x2cc)��B ����� 	��0j���Ӂ(H�(g�wğ�B���::�	gϨ���0��(���;��ɏ��,)H�݁�0��|݁ ��R�ւ ���|>@L��VL�� 0)�4�'�A�Oh(	��(�@$��T[Q�A��`�m�ds�3.��XH��@�Di�*2�[���&�#�n�ۭU@0RN�l3�:    �.h�ÿV6̸J�Z+dL�6�v�'�p�Vto�FO�\c���$-16xf �1I�dlp-�hߓ��0c�"���mm��x��al!�F��~���-H� ��iJfl
6>���j΋	�������*d�U���^�`b����
V�������a�1.���3Α�-,26�v�ɸf�����
 ��n� Ќ�����6	W�`� >�N��+��`��1�:X\�L������:'6X;v��j���M� (�F��Qp�A$L�nHא5H��Se�\72�9�� ���z���j��'盁R�H�N �}�[<7�Q��� ��RmQ��vC�=����R ���n�q���$�l]���7�(��b ���`�)c�
>�:}<�%�k��DzS�R�.J#8�t�0��(
A�`;�PN�mE�,�3ʹ��Gi��'cn^qG����|c&F�(yJm;���R�6z�q���
����3NJ�+ɤܒ"��q��K)Z��H�i�'/��ބx������˧/��ó ���ҵjG ����rj�( �{ł�x���3�<D�]3Q�����e�lJ�Q���ـ%v�i|az��k(}��p������y��[��c�ΐWJ�y���ա��7?3��)��{pa2C����}KPy��G�:�f��Ws��r4��h"�0�5fb�r�'!�6����<{R�)�	�x���Qi�AӄLŜՄ"DBe{���dV���Bx��7�e�х��n�Dij%� �iyF�qU�h��k����E�"?�aXFޖ���ՙ���X8���,��a�^O^cў�&i^k�sS`�Gms�xP0�3�s�KG�1r�H�ř�̘��x>cI�K��1��DF�3���Q/��؜$>a;kp�P���c���������0�����|io����t�:�
�&�Km�Q��(�/r�E5I�ep1F�Ҿ��K�u=F*�"R��m�I�+%;9���@9� /��)uv,
Z &\�Tל)i�'��>؁��'��.j{�!�4?y�x��?�+dB�Xi.[�f@���YB��)���`R}#N�	3ZDfH�KW�!�L�(os�� t��bJ2P>C��xt�LO���8zDfD*h����72�d�v�$��	e�sɡ�d��8*-$g�� D���B2 ��r�,G#�c$>B{�� f��	����D��H���N�	��ɑ�h���Z��3"�WDDF�I�8۴����ג!<%��m��DX1�Rz[���칆�֪"�dn
��\��L����1�Γ"�����<��ޙ�9\DK0ʀzl�����q�v�\Xp1�Da���cU$0��CFDY˔�N��6�ң>^������`�@d�{�< 7�#���#go�=�5��
�G�	��������	�\�9��j�È�o:�A�Z�7Y2 ���e�7��_�41�[Y0H5�(����p�΁�y��N!ш8)�^�9�u�D�=���
&�:�$������(�3�,�8_�,N�^��V�Zi>!��ћ��5L-��#�/.35$�IzS�;%�$� Jߥ��u���L��?���)��^ 8�	����T��/8B;J`���.�@�#�0�<N��ǌ��� zn�4R���9:P�J�u�5��j�Iy�X�t`���g�{�q�� �� Z�Ƌ��a����&dL�1k�Aq�tB�ŸTWP��d���"�����Cy�>^��=��)��M`��Q޵<����Q�"cP�Cy�Z��(�M��<ATD���W�ᱎ��RV�Ew�3��l]JZ\�2 �Q�M�
�Q�e�+� |�3Nf�WB�`��qQ^�+%9 n]F���c�,�Do��8��D}�)��	{�.6�9c����&�a%<����q��IɈ��©
�S�^�H�b�qRZ#���#���(��׬d�	'�`��Rp= pm��r��j,��7�M̄���NQe�-&DcFU����X��7-sɨ��ū������t���0�<��q: BJ�x����x+�C9&��x��a�zJϮ"�w�S)��TtrmU��X8�t?�	�����HaE[<�3"Ǹ���4x�2��s��	�r.vQN}���{��hQ��qQ:��ф����)����h.;R�>����Kݔ�T����)x�E�4V�٤bU�i �c���8�b$�c�p NUƑ�� ���2)eCo�W��Kq�q9��L��4���	b�IB�"�5f�^/�EbI���EBc�\Oe�XV����t�"�E��T�t����:Z%�ǡ�0$׎q�Z�rN\i-�dZ�ĝA1��{���0Jc���z,kY��!�J'���(���������X��1ե��-RF�sfX��dEΣ(���F�I �\Ag�BƩ�(�}��)KH�)��:�<I��7-%���	��S���|�TX	�8or���^w9�e�KE��^w	�9��E�JGC�Yƒ�<�&�܅d�r����5)���Y�M���3FK)ץ� ��N�6��F)4�L��x	e�k��� �e8��i(�Lq�5��K�t��b6@!l�@{J�8Cu�9c3��'�-��w�4�%W$P�c�t��A[�����B�<�0*��$}1*��
MѵM?0�?Q䡻dB01RŶ�C׿e��̖�OD@9�縃ɟ�Nm�VF�p����,a��������"�X��]����*#��+{N�`�kF'h���+t(eXݵ��L)�Ljez�Kt�5��|����]�!D���?�Y����6e���08� eU��y<�EEH�1�ep1�TF@٪�j�	��*�v�����c�Ĩ��SA��p(s�Ҳ8AEPC�pd�a'㔄|�`L*"��V��i���D)(��_
�+U&�>C�t�)^'!�Ƽ��v|��S��~�KA�Q�,�N��xE��$����2n�=�pY���q�ݏ`�� ZJ{�35$)WRS�([&p\Y0j�paɠ('���+9O����൒��q���!�D'�W����2:���sI��)�/��!<�cx*��};Cr��Jy|dP*Z8e2��sI��X�'`���tfRG{��0�(%��s+T�A31�U��B�"~=e`�@� e�"��jD���=��SH�b��Rx`V/e������e�	�`S��9I���4��u���{�V�����w%'���1q�dʚ��������!N9�&.8:.���tiO�a�J1���@$�����:��۶�HY�<3�!��3��bӂ����_�Hy����"(�%��
"��g�RQ"(d�&ʠ(S����a�31{��!x�.��r.Yfn�J�C��-���  s�ᛂ��C��J�	��	s��� �f`���� �	����c��������3 ���6��˚F���	����=%�t�89�4R�;�	��1���{� �S L�T-�(���d8�o�� :�zu�	��`LgV�C,@)�����Y�	�?"ϙ��d�0�ڡ1����;��)��ԖYYM~�ȨXw1_c��*�-��RVzH�u� M�����Ac̠,�y�L��Dr`��sI�#P�|+��l_��h�l�Ǹ�6Ÿ1�{2��bOH2��U�!��"�2ʩ�3T���=����Huk]����|��!�u@�0R��,m���7YҘu��P('H���۶����]����r�T>�v/�	�u@�(/����1=e�S>�v3�w�.�k��
����s����D�v>$g6ms2�񔳇�����Sv��l�3���Y76��3׽t�;�		�4�|�;!b۶����������qPN���:! FH�sC���N���Ĩ���%:A�E`�壃m�NPc�2�Һ_'����q���o-Rz��!~=��/�5'H���(��Y�9I"X�~N�9I$$�bG>�N
)����z�I�&�������dL��xN��'�� �y�k��qRď���v    �0p!�fz�A�XO�<c��ރ2H_Oy�M7�=��i)�eq�A$(������@�6j���:��V�@�	�꿆���_(��L�dh:6�Íg �M�@y��g{<�����K�0�J��H�GR� ��e}Id�4@B@��]>>=����w�4`���=�yʸ����Ϡ��l�a�c�c&F2�A�X�X�(�Y���A�7h���6��$�@�(P�����yB��A]׌^M>j!"���UJu�D#(J��m���:�a\���NPt3P������M���DCl#L��-2ՁbH(�p۬0�.p|Ӷ�pJ�{M�A1�k�n吀R��EF�풩n�0 �'fU~=����w�@e��'h���!cd��͌R�o|3��.�+��x<I"ch(/��d>I�b$-�_�����:6V�`��x�׷,pF�@�p�{�[�
ː(�XvlJ^NX�yN��<iX04�0(�w��p�"0�@�������Y�BUa��&�(����Bzر>�[�$2SP��&t<����E�j�]�^�8������"ӻ���!!�O���m�J�0�v�����=}=��αj��"��J�bxLyq���MR�2@�,�W���d Ei>$��z/I9#o�p�^�tƽwM�.�(�L��(�֖25:���sI���Q�ri`���
�(	R^E�Ԃ����2�#Z֎����R.p�ʐ�
��8��k������HMiG�AZ�Ņ�w�;���<,ʃ�
K�B{*B�L�D��#�ڮ�:)Y"/�V/=C`a��57�Ae�K�QI�.�IfP�O��L�|�=��1��U��b����5r�@������A�AU���P�M�M���	e�����5���ï�r������K&rp������19~=d���{�4�`[�#cB^��`4��"֮E�Γ�=��Γe��N�	����~��A���9'��{�$��rv1�V����5�(��X���2�bܕ��y-�Rf4�	�Lt` tm3)�ic�'0�0MK98Z��+p����r�C�-�	���&5���Q�ނ��A�֡�nh�mZJ����8#B�M?1B(#D�X ��(�b�ѻ>����J���`��u�،�bbK�uЉ�v�o��^u� |ؓC3P+��:��o(���خ�
XD4eN��ʤ�����L��
��Y�S\��7C��R�
6��k,e�`
��#1M��`z�qzFq!��E}�t�3&���-"H-6�w���
�_;���D�!�R���GK�%����J�\��顎��#�k|(�ar�^:9�U�BH�9	���u`��3w��wⲀ�GD���Ҥ|O�F�S�����l�O3�G3��`�:&|c(���T��t�j��I)��_.=�,9�ȕjG�%Y�z����'&����\�<�B�E���2�^)e�{P��r�|G�������x���
ZΜN��X�]�+Y�<~O}�Z
�ځ�k=�����K�:�P0.^iu	�IX☊����� T��P����i�x�Ez�����e���`�(�d=؞gH�6-�U�����Zʩ�]sW��k����L!}ksŖ3�[�)��1B4���#�8��X4�w��窺�!�<�3(ƽvfY��&����P�9�������`O�?���N�ف3󂑑���0\��0F9�/��ؚa��;n�/���z�pZY�$�Mk!��*���xn��Ϥ��%�pWM�Jeҩ��if�t盞�Y!,fI�[��f�g�q��H�6w�Q
k3�������e�`y�'����E�M:Ư�jDx���"~�9+�|�EAI�?�x��#�R߫e���ܖq�� ��m�Dy���Ji�/ ���(-(����3���Z=���R�)�˱`*�1���䩗]c8I��y� �5�\�� �\��3/��e?x^h�>Qn�����|��1N��J�#P!e�����r:���w���m^�1�8(��VΧ3�Þxe`��r�� ?��,�0�tǧ��+��'��d_����/P�edY��|/�����R>�|�!�>3���o2���,�AaUx��P@1$(�H��+�
�|���~�_� }=�K����C����g���!Zf`���YЃ��@5˂d������� " ���{A$��q�֢��^P��.�����b<IU���EG㲤="F���RV�PA1��2^$� 1"�i}��7������B!~����[J�
3$�=u����@)��X0��<~� �P�m3P^fǨF��#����u͉%6��H鐳Gێ�1r�1j�2��1!Q6�J�$�� �2G��n���d���D����ꛑ�~�MB��Rv���6�؆���^���)�Ѳk%�����V� ���_o\+�JC�S>�'2�k�Ć���|R�x]���G�a���;&�4@�m<��@�(�'H��L]��  ��g,�mγ:�7�a��:�n$�xj�?���^�Xp�t�N�:�lk�u��f��ZWF�ȋ)R!gF�_:b����Y�lA���$Jɺ'�pt0ߴ�����I����kAM���1�,fq�8@C� J�)��A:,c�e��8�i��=2a�hne2
�]��K[g���Q��fT4S=J[-�K+e[M*&`���j[H]Zߌ�6
�gj��Tl71�).`J$֪�v��Ju"�zl�d5p�r�X��F�E��ɸx��Hm� �q��T�J��X�d�|��b��ԅ�Ę?�Y�Gsm����8��|/U�UGT!(�����S��Ì�rM������R΄��T��(G��lB�H�ǌ���Z�H9�yU)Y*W�z�_wN�X�g$��L	�m�J^qY����9�B�^ru�qq��4�H@)=E���h�1C��#2��U�ԇq����"e��e�$ʇ�l?��卑�m@A�F��C����{
��WJ
A��<�m�b1$��kz�IX�zi��� ��N;I�O�^Ӯ�R�=^;a|�����#���F>�Ræ%}V�@i�6�J? �_F��z�ҥ����_nlHWM:R+�=Kc!.�y~F�����q⪖����#�Һ_'U�}Ƥ�h�@*��~%+.��·T��(E�G�j�Rm#��sƨ��)tN�2��~��+�uLB{� ��&\�X)硿h.o2j�¢��L��(�ܮQO��8�|�N����aROX}��7�(o֜Y�+�7�)ˡs�E���DVƁx_e:��q�7��Y;4t��z��|ΰ� Dg��R]�&U3����C���(�#}WcW�b��ɿ�x��oz�z������س����k-��M�����0�Bt����
�[�	����:^���sZ�&!��d�hv���淃<�$P��dSz+���Q6�@�a�����8:JYt��=X&f��N�vSF���a�" 3��|@L�=�<�|Ӷ����s�3��1ԞY�t��k�-L߿wb�^��{9�m�f2�޼t�_�����+/���H=xVf�5I�68Ϭ#����5�r��:^8r�zX�Y��=�niG|S���E��5�n:�_�W�w\e��
�>�^�Y	#�m������&�Qi�_�J�RJ�F,)J}֎k��;`��Ճ���3�8�ˇQ�v|�+��)=����ԅ�(G�v�J���X��[*ku�R��KY�:�ɗ�P׌��X�F)b]��㯪��ggd�ˈh���֔����I��5%8��p�֔�UJ�7�ʿ�`�b�0ͥ�G�=�� e<m��Ix�i��3*������%au��3�u�Iue�BEX�u��렭���8�3\���k�Do��|�.�>Ny�LQ�9k����M�D�<f6Q�����f9͘�+!>Ҏ��~<��'H��lg��0R��@�n�U�撁�����4�u�6jӮ���c=x;$�џ\�]K	    bF�uC`�q����t<�� ��`���;��	��3�I�����D|�j�%s|r�����ٔ�Fī���'Ë�ꊭ�$���xuU�ɒ�ru���^�^D�j6������Ϲ�*�=y����%d�Q����Wk�����L� urkO���R��P��(xHeA����
Q�"��ت�΄(Az9�L5g��<�+c��7mM�Vj��w��� EL�
-��1u��Ό���?O��g�|��:U&:�{E^M7`��v�	�XӉ���@#>R	u��`��ە���Pᶖ�*��z�6x���o��?�����_�P���x6�V�H˴�.p���ª�͈�͆�!.���Ԛ�����Ĉxj&}"�Bn�f�5�R�G`������L��oMkc�s,0�1a��[�縇����Nr�6�ad=�M[�I��ᶇk�_�ؚϥQ�'dE	���"5����Ή���$5��jB�/7:&Az��:� 
� �4gh
��לk�����}M�+�w�AV}ӎ)b�5��Wm�*"k�P�M_����
�9%hN]*\�?��b�T��(��Y��L�����V���<��4N�a-]]x�(w�j��{�(���r����3�ai��A0�#a��K�36��
�C�OxKImaO��.L�����K�Q��.��࢘Q��V�0���тX��?X��'�r�R�B��w����F�`�����*��XmU����4h8��N�V�x_K�5F_�Fn��)b�)uy�� ����PWT/���4Ҍx��IwfKK$���$�[ZJ b�@g]�'B�A��]-�;P?j��G��W'��v�?��8}��B?�����Ai���~}㕥�Mm��A�
z�|c��e�I%ׇ����=K���v0�D��=�����>��_�lSÃ��M<u9t��A[Ǿ��'3�{ң4����m��ޖ�_IY�IPV��uGxW#U:��_k��&z�&u��R�`�iP�1�ɝ�-�ܠ괞�݃�R�9� ���mn�NF��-��:>b��R˱+ �V�=�?BQe����+^	�ꚱ��n���%������L,_�ZGK���N_���	s.d���\[4�M?���-�]t���M��ˢ���m[�Ea*�B�+���:���^�`l�ɍk�5�ʅ_�����.$Qx�'���e��T���i��|K��۶��͌'�O6��N�X"��t�c.��y�L�4�~�Iݯp&C�
q�E]�Lu�[9#T�G�S��g{�}�A�~�B���yi��]$���Z����<?%����s��.a(�O��xN����y�&u�é^�9P�9fC�5m�}�ה�.i�eƫn�K�K2-�Cc�g�m�A�\ ��s�ȒA�Awk�݇~�v�6N_�Єc���득;�� ��������4����7��Єt2@S�H�����GD��ET܃4�хh��f������ú�R������V'K�R��n���tIu�.��s�~?J?�^ϕzuS הs�m��+��/-�;�Z���ˣ���N���=�)7�+��)n���	��\
A[n�c|�VU��	���_Յ��}�Wxq���3�A�� B�Xu�q�4�T�W9�Z֓�'�^�M2b}�evBS�<d���
w����Z]�4���Z��:�4���+xu�B){��ihD�82B��#Z����D��.o]��^}��3�\%{��2���^ڞg�ԩ�=�Vz^{F�.+�&�J�E�W;u\>����i�����Jī/*m�I�G�����]��.��-%�1jL~wM�K)P����
OR2�@���yziv�gH��$m�y��h�E2lf������J�:P�>��B�%����m�R�% ��w}w3�VX�
��J�!+�I���?����7N��@�F���w;�%��\ޑ+��B�n����J7x��0�ݱ��Nq^^�`���Y��A����m.YϑY]�:��F��A�/u΍t�ï����6�.�ě��w����g�ꈫ,Ő���'>�
Kb;"Vwf�޶��x�hꖼ��t�c�Oߑ>�]uR^o��+~]��%U頻�ڻn[K�64V�;s�ZJ)���׋�ۗ5����]sj�GQ㯠pީ( }�$j���'c��c��-����� լ�V�t�F�(c���ŏø�<Ng�q_t�]�^6Դ�a���w��}}�t5���q�y����g8j>�ukN���rc�91��?ƸV���%�j�)����S߸�\�[jx)XM_�����P�8�����\(I� �Ṯ��$�Ɂ�=M��1F��1vPc/�� ��P���Z/����)����K�>6eR��Aj��q5+�?.V����5}*�,�@zXM?
���@k��o�6A@Y�Ԝ��X:�:\�'o�F8x�ԶMϜ6Z��FZӝ���l2��m|��ă�� �k:J�����(�����tU���O���s�;��G ��Q6��h(���q�Jia���;uc3մq��j	��9FZ�&ZM����d��]�[!�8m�ኵ[�e�@=e���ZA��-k�HEY���	ʐTl��R��BPNk���:x�x#����������g�E�"S�q2CDH#q�-z��<�M��wM��¹�����]��J��X���R��s},5���q7h#�V:\3k��T�f��e�XV������(���"� )��)!��B�/��5=Dn-�H1k�i�x�8�
5��%R��|�xb\,N�J���-�NR~!B�|j��fTC/�х�T�����=C���h���䘤�������gJ_��L�XC9(Q�$Qm]������z69��ܳ饉hV�;8�8,	��]�[�D��a�Լ���[hф�a�Dp���
9�Q��[��m�\�M���e�UV���hD9Ǽ'c��"d_��>3�ҍ�j��U��w�̒�l:L��ı7��@G��cu�T�0)1�%�8yew��"F�|����
� 0�X���[E��10�7R؎�z��JgKy�O։�!�)�u9��W���-�<q�(�6�<��\M��a�/״=�q}#T3cnL� �FY���m(s~��4Z)�����㿧�&͵.��d��cl'�C����ܸ��I���N�!���7=#����='�)��1��ie�c�����*1:+á��Q�q�Au�kt!8'�\�="ie � s�Q%���!��(3�Ք�I�����p��P�Q�a�d`�tX&���#fH��W�� -�������F��j��`b����C vƽ�_/H�����O�d ����"��$Eʸ��D(/i� ��Y_L璞��/9�sIZ��,��tރ��BK)�.���Ì�P^=)#��B�d��]"��(��EE�����0�|��Bj� 5Љ�M�;��e���a��������lZ!Ab�4���,&�y>���>��b��б'��x��s�A`��o�2bb�d�Q`��ҶU£�t���(���.�t�X���FF�����f����8�K�E��6A�θ����'�0gP�7�e:� ���|��%%dT��S�^�sH���Q[���H�O��;ݜ[�Z�
f4�������DFcK����5MGY�޳Z2���LS�Y���)�@�qvj�!s({o�:�PofM�wU^��쌋Xw��E�����2�Q��l�R�}��%���(�D�:�KI����UJ��(1��gjzRz�9�t�['A��j�(=��
�_�<F��;	iSL�)�ۣ��k,K0�ᮜW*².^QP�Z���:�(���
�+��(�՛Z�����
��Z�0�Zʂ��&�
k�S]y�	�P�\�+�F �fǸ �#�#�5��=��u
������|�B<<fM��g\��ó���*����:�d`�WP���:e��d^܆YG���S��L���������1��=y[�1ׅs��[;��~x=�|��쫡m�ۡ�WƽyXL$��H O    f�k����p���_��yғ�s��aGe8���N�皶S�����j^���R�|z��9bSS��
��䂽�jV5���p���k��ܐ�7kY�܄�W�Qo��.� 5E/�u�t3��k���Y�g[�U�;��'����"����(/u����+[7����ɡh�� �u��B�j.6��$͈�^��'{I`�wp��+T^�>o�߻��pB�iZ������R�������2�D���/�E�?=_�*���b�1r$W�n��c�I�8$�5{mwW�l�rP�]U��vL��-uсv��x{��'���{�Wx��_ǜ d��Yu�^��N*n�����K�G���^��t�ǺOfݩ����^P��'���Tq!H$�m����]{��9���&c/���j�:�*Ӟ�P��xk�9�St�BF�#���Dh����>��*�IE΀کC]v@���Q]��̤��\Ͳ5Gv���4b��PG����RkIe2_��T��٭�u.l�I�͹��l�K�[W��\J�:���s�K�����%��ҺG��W(]�AJ]�:�r�0H�� R_6�G�RP�t�"��RV�;�W���@�Ʒ�Wa*)�h>�{%� ��ེ�79���Hߴ��.�6Je��1���5�5J�K�P�X�$�"^u�� c�� ?@M�!>A6J�,�V�Z��Q�c�AGIyL����r��i������vm!7Kb*�}lj�$V@�o��`D�$	 uG�!6Ir4�
�[��$)�K�5	J˶�T�	�;�N� �"���E�I m��b��wm�
�lm�W���Yy@J�؆��n���+�����|��Ge���Q|&���[;V�H645�;�'�[S��kz}.�,2���ft�P�Vr�	���hyg���G� �sw�lt	I����e�R@akF<�+����1Ɠ��XV�oW�aFB��*S�>�!7?�|��+���M�ڴ�Uwle��X��L��u��0�K�.����:I�T,�SZe�IJ����:��;��t,�Xuѩ��[iG�j�d�
]���S�g��B?5���1B��9���.���Fj������7��Σ�Zei�A��p��U����k�ڐ.h3�񫣨25��Ps�U]4��R'y�w}�� 4����}��D��Z�¶��/�[!�I���\[�K?@�.����_?���ÿ���O��˧�Y��g@�￾_���Pp�d!�f�%e�
ݸs^܃��[���jg�+k��u�Q]ޓ �m���.D���>����L$�YJr�L��U��R]+�}�[!������.�R�+���JBF�E��M�g�L�� uJ���E鸇_A�o|i>9*Ru�.��IQ+��1ٮ���E�O?i��vvD)|�aݳ��xHB�/���NMem�.���/�檽���>��(��viH� �%{}W�wM�K�Qk%�c�(��¯�������4j�덺�XD�>j�Pt�vua�^���[R��zw�/�-� �c|6A{ Z.���s�I�>���8�(���m�����wPү��=0 bx/5��zua���HW��G���]�8���C�=�C`���[�3����Ks��Nu�x�<��g����N���\yh?���	�3�r���wdx+g����g>y�9s�m���k�cl@5�����N������P �g(�LU(���Pj6��A9s�@��ƚ��~4��k���-�������4ж/ۗ/����/��~��7������m1D�R3ı��� ^��"�r���c����ִӹ��A&�'g����� A'��nW!��P+j-�V�Z�9}�+�0�߽!
�&B�y��e�
H5�4$h��q����@�E]�k֖ڝئWֽ}3�,��c۾x�����{��.�|k���5O�V����]ޡ�Z�d�5�=n��<�Lkݓ05���G�G����h�U[���`�{e"����ņ��f6V����Q4JGW��O�lŮJ5P���%���.�����kp�8��<�|�����!ُc��	�r�[p��=�[5��	�Pw��GC�d5oKߦ�aZ������ަ�aZI
uS���A�K���U���!.�ρ�"!���a��˗O_�}�:�w!]�M���?�������75�J�>=�?����Y��������k��9@�m��(��B�tBaj����b�#W����rc�19}��1*���1�� c�Yǒ+c�!|�Ș��%Wf�3��W=o]�0�}����
g�� b�暴�
�! �(�EYV������J�!@�<R�sI+	� ��ˍ2|+�� ebL��l,I7L��Wv7p�Wοro^���+�����o/Z�ڵn��-�&酩?���D�X1��Ɛ���ؚ�wۣ���f���?�m]����M5�L<��5�C�统?}���Ʀw�[�?�78km���l��x8�9�z�B�2��Z�d��"��eA��5yMiv�g���Z�,�Uә�ɂq-xơU%x.���k���̻f���?���󘺵0��}�������x�� �F/����T����;p���V�JU0�~�T�7���F9	[0	"0`�k>h}��y�& �� ��78���p&UJ�r0tB�M�9	i㄰��Z�#i�B nk^g���3�U39<=Oj���|��d졬��Ԝ'b���@C@�Aw5[���!�NHu�֭ژ�4���*$�&~FF=Ġ\��e�␏W�;]���X"�����Žm"ƚ�Pm�o�!�I�U��/��^ 〴��5~FJ��Ⱦo��S��������eR�t4cT���l�E�*+��h$pD���t@@�����(�p?���f4S�;S��dF���Eۙ���E}�S�
��n�(��uu#��L��p�*QsYL�ƻ������0I4��TUV��6a�$v#4!�[��k����}����Z���:���L�HT[�-ONzaKϥ����@M��6�j�o	�^���:���s�~q,�yU}$j�si�� �gЪf?�� �UDY�m�3$�iȻ�E��H�P�N
]7�.��Q�cFƒ\p�R�t�)����˅��+U�T�jyHvV���aê�$qn��i��SEMR%<b֭��	�a���Q��yA<|3&]�J2�Ql�64�=ތ�_�w����`�nZ�fpCۍo����7xJh�m�=��C[�X�MpǘA�8c{ǐ��F�i�+3���Pۏr_��`����c8����ԂC����u����,13rU�$߄��JʞQ�:�ܳ2��LR,ة/aS�
�X�෮��(#Թ"�{��~���rP����R!ᚵ�*QR,8b�T��	̦}�[�(#)�qM�#Ww]8����슂�������އ�^����v}g_��ٷ���E7M�K3��^I�	��f>�%���|@�>4C�YY�ԭ�
f�~8���� �|�3�8U��Yւ[�Z��˂�|�QS�����G�k��= t]��|o��'N��D�T��K
Y��4����<�3v��D���Ž��[zg�	����k�^�����]C�nlL��>w�QDek*�;��DT][Q۱�����	{�NI>���~�jb���	���9pA�������t$c��+_�'�5�-REە�����y��v�����ߐ�"2W��ϵ��p�kd�9�������=F��0U�A#AJ�?�ƍ������д5��n��� �"���m�hG'H����z�:AN��i�J�4T��{S�� �f�'�Xb�k<���UV��II�*!SY�9�����\22��(���%�*�׸�K�5�+P:;H�P��J���}|YIzR	j͇;Xu�M��+�y/V�Q�Ƶ>Dϒ�
?@��kY���1�0�̎�}��˧/����oXK�����Tqs[���7����q�/m룃���A���w�^�����f�q��֝�Ը�⦾�;zZ+k��    �nkC��?O{g����U�j���˗�߸��7} �i_�u����/�u�Zke�5k���[���?�:^�`�[��� ��ݙ������+�x�F8��7�P1�p�O�;��	H}�����Wcۿ�^����j|ۿy1to۩��h^�|��U�ȧJ��F��&�3��4]�lj-=�G3��oL�:o#;�a�a�)e19����pM5 c%�5}|n��/�*GPSI6��-�����oW�p�3�,��[����������s5h'A�SHo*֒o(�ㄽT}ۘ����������5��M���Xk\�lk��Q��_�Y���a�Rk���ۮ����T��N#T�\]����s�^�ԝ�_T�=>ARs<�Ŧ�P}�]��;+��5	Խ\)���a����.�J����V��C��y��x���ұ�ڌ�ǡ�5��*(�\eRWB;���r�I]��d�b9�+25uZAd��j���a�f���nk;6S�Y�;6�$$�sy���٥k�ja)��+/�&0�@���P���
2�A*2Ƣ��$~c���2�����25�#�N�R�h{�Fi��K:�3±�hnR��"��VӍ�6H����;W�	�#uS+TD_sܖkXo.�X7����]"ЂO(��c�[��1�<`͓��9�=��b�˒��@V��4�����]�W��-�8ͧ�7L������ks,e�u���}ck������7h�p��u5�7]E��ց%CԻQ�F}�p�j��u����z��B�q$�t�i4�.M�}	��4��
�e�SXB� ��c�cg�#�E!�n�NW1�&��[ꪘ{�Bs��<�,�VXfo��UwFEuӁ��R�Y���ե�#�AaΩ4��7�$���f��(0��u�=��}���oo�Y��Um������T�`f�	15�a���H�rtp��Ӥ���
:��e_ �H�2��e��*b�ZCt}�؆��;HKi&�߱q�i�������5����;�&N�u5E@7���[y�:��o��a?��vx����M��틗��-M7Wv�O�jֲn��r�`ew��sR����V��NW�U�{�ו�������+�+>#�j˰OW��O8�vV��̡}���|!ຊ*�6��j�ʆ�	\͑�ۀۚ�s�
��Vl�iy]�	�n��|��e� �"Z}"�Ȅ��ь�brØs6��D
�M��0���vꦦfg�n��z)b�y;�b�
�)�Y��!d/�'��z�����t��N�����D[���rF���؈����M5��u}�6혟��fH]cj��U�t�����tvͯ:�Ph�7Y�2�i ^X���s_�*lK[s�63�� l<Ӈl��]�q�zU\w2Ccj6��m����� �/��ɹ�׬��h��"d(5:~c_�=�`G�c�ɠ_oŌ���57���F�OA��yw��������θ���A*1��i��7�VfȚ���s����q�|�_/�B��wis/�"��@H�2�x����A�S��k^�I�������]��Q�qo*��뭷�X�ŻX�ׅ�fk^��n��7���}����Uf�gdŃc ��*J��O���VK^;?B5�(��w����I7e�9d�v[I�(�w���rlZ�\��9S���6��?��{����ɢ*N2�W�Cl���	z|ZGΥ�L��%�fZ���g`��|Z&?����5Mh~�^['��{���8�����m�?���0�c�8���񭔎k���"�>�s-49glU]u�\��)^�u#K ��Z���)[Yε 2ƥ(`9ׂ:H�jޙ�Stu-(���yY��� �!�̓��:艌��46�/h��d]3hap�!}?o~ta1�N#fʻ��F��3 F�z��u���x|v)�5/c)�82��Pƚ��+=w���Զ'�,a\&����y�(��(C��s���������u�����W5�a#^��a��f�cc������d��o}|�|���{��]��36(��j�`c&D����:8������s߁�M��Z�Ci�Ԟ:_��Aq�)�Pj���t�=w��6�)!w�)��s
�G�16��+i?��=����{bp��x�4	e�m+�{�\��N!`�8KV�!� ��VP�����'�7��.�~��3�)��j-|2@��͂60EJ��3�]�eA
���v�Z-�������VH"F�;��:A�ϩ+���)�;�z��t����	�������_�o9���U~q�����RZm1��Oٮc��}���Qӗ�t���2@�J`(/G���r��(�r>O�og��p�R2�\{)�N=����R�ݾu�ו�%�gL{�m�G�'�;��B�f�vj%Iνhs����"Ϲ	�]����Ћ�\3մe��M'�c��aT�$�Ǻ�Bދ�=h�,3��FM�U�}�e����ˡ�gd���!ń�!T[xۤ�ϐ��eML�E�:!���2���5���d||�O���OB�~F}�$��I	�뀒����l`X��3���eX��e�S�#�zXGf�[�mX�r]�+^�`L�$�^g��)<��֛	�@IK|\K����:(�Z'd0����ڸ�@�����u���Ib���*�s1K������)���	|\�
�	�L� 2 ƽ��@8��ƚO�ޗ�ANd����}'�	���N�@Y�'�6��KH`�	�����3�|���XZ�p�̀2�`ץ$M ��Q*�%��b�
Gh��3��N-1��g�D�����c��)|r�P�f�-ȅ������-��2_��=��t�P6�oAOd0�,�/��v--\wj-)6dp߮�C�~F��qd�:aA����K�k��`c\��8��-�#�Ȭ�DHy�l���Z9$0���v�Y����� �����Z)���T��ܬuB��<�R��F�	#�mҁ�f,�`vЁ�"fl��}(0	R"����H	p|p��MGYEl.��{�Ӵ����r�Ƹ�.'��X���Xvf@���Ӂ���15ߊ��PJ�<��}D7�I���}Ubp���)/�o�������+b9P�:�]�7c3�E�#v� �2�"p�O�Xx+���I��x�W����
�2���3�IG}�<_4����� &"Hŗ/��[�oN���$h%p�ΟO٥٠����P(�J�؆/��戞 ��P	^&w0V���MKYj�l���O*6��o��f�=�x2�(�4��Q_ 2^��K8s	�Ȉm+����
����K�b$ǽ���%�܄�!�jf��lE"CP錵�k%��u�bQ�����w�G�&Ƶ(J? �ʧG��m>)�=�ˬγ�b��%���xj��rL\ 1x'~�e.o-�[�sX���ߌ��RwD�F
 ^�pT[ 1�ƭ ����Xߙ2|o���N�Μ\�t��ŝ��������?�{���Y�"�j_C�䵢4'*݇k�^^6�N ~� �ᷟ%�5�Q��c2⌮Xa���{���j��X�P��k�|@��˯B'C�lJ��7Ð��;�6�������4H�=4���"������r�A�e7]He)�on��R
�(�[�� ���q��$���RD����rQe������~��@��}Jj߻3�L�d]`pF@e�����C�4<ůw�׏�h{j@���,_�+|o<�=PՆ�k�����^T����{`�1�;��O9N��,�^N�&!tu}�SjB1}��8F�ҧI`���Z}���n��1���r��{S���g�b8�*��ժIRs�'�~f5I9e��UG�!:3�z\L��V���`p�"ҷ����l_m�o���0��Bҷ��?�>�PWs�����O�w��G;�W��^�x;v����˩����h�o�BF�['B�5_��ċ�o��k�z6���^�ڛ��z�; �΄|�"��"�#};��j��n�oC���$��9�q3�    ;��i�`�HK;��12�����ֽ@{f�5*���DoI5��F����j8��k��x�e@O��&�"cc)AO%��.i��]�=DѠM�Pӕ�5>[�&	�{Dj@q�V|�/�b�k8��������_߽�(�\Ws�f�>|����7h��4��敖�@�S��k�nK<�uϩUPO������[��(��+��_�[JA;E�5'��um��$P� ��O�Դ5mn�O��٨��U�Ћ�� |�����������cqq��]M��{&�0i��U҂T���~�I��3s�N3v�r�79঄������ɭ�ɷ'k�jLGnrk9��u��Rȭ�^���U^Rpk���Q��?~�=��*����b�_����ug5ۯ%T��=�	կeD�j>��*�z����u��i�Cr�{��	�����X5���[���s_����BM�`VB@wf�Z�6��� �3AUI_%gz�δo��vG���
�X_�iKz�.H-�TsuS9m�ѓ�V��5v��UJ���̒BS$�!5�Vb���梜F��#���8��n]��
�A�ci\�U�u0h�a5�)� *��;� �XwO<�vx�Q/=?����ƒ�VP��A5�gT�p	��^*I|5/Z�Y� �WE���J,$jD��pz���5V���[6�J�ZK�����2(��A*��5��t>�:	���S[�����TR��*�JF�Ь��bAC�6'&�L�dB�)И��T9{���xиg@��~ ���[�<H�Z����P���1Ԫ]���k۟�д5�a�?LS7���½����8��}��՛W�Ʒ/M~�!-�J!Ok����e��b�r��M~FHpf��/r�u߂��O�m*W�ҷ��r��dB�%A�H����W�z|�}�!@��r��6A4Am�W�w���U,MB��(k��w3�;����f�u3ֻ��QP��!��ܘ-�jDZR~�$e�j��G��$���k�����$I�V�vڙ�O����Ve�z��X=9П�6&pc�'n-Z*|����?���tL����q��p�}VoXyz?A�l'|G1���� �T�����r��vm��S5�c�����O����b���ƚ߉cV����jz���V�O �G������4
�}	���S|®1%��-`b�o�<%w�0b!���j���S�X�jĚ/�ޟk�ĵVu�dO�`��r%���G0f�C'������ߍ�:��>�R~g�u���'g_��%'vH��I�_��"U 웾����α�N��F1�������o��q�VS�^��AWjh!ʕ	��r'-�Bq;bW�.��*�3އX�im$y�6��&��h�T�x@�2���eG�i���+��pe�o��k���P��RsY�`���X1l+գc�G�2?/�[��luO�\�N:��KY
�tF��O���3�I��X����N�Q�@e�sscK��j,��-He��]e����BV��r#_&-�(�"=�Y2�\��8��;��T���pI��%Ɗ�i�H�"��zNª�]"���D��w�U��Q������߾��e�ʑ�o(q�������繮7B��xϹ\@��D�V�8����Q����� Ʊ1�Gpg����Ǌ�b��$�S ��tA9Hۓ8r�QG/%|Qj��<�����l3V.5����w{���}k_Lo^�o^��^����7o^�c�Ҵc����C�3C�*���@��K1x8�j]޺Գ�C�6A�<�{ύ�C
]u��@������&�u��mp>�nzPN3�ʏ���<.!5Mm��y�b�֯�X�������ɟ?���N�c�����yٵ��-�|��ۮ���m1ӬS���<�v��Cd��Ժds;�B�Z�$�q�S�3w-_Dc�i��>��x)Dk���V~�6@���a-s"4۴��MoM� �Z�,�֩��9ĵ�	����3�[D�	dXk������FA�Dh���U�!V�|ސo|�>��8��M���28�a���V������� �'�ֽ�;��#���+T�e�Y���G�G	ee�;���>��)A�\?�g4!����1�>���̶r���&����M���A�7�"7A�|�~2iK����m�p��sG8�ӵ
�Kx�:2�5\Z�3�L3��������f�۶��06��R�ߦp�6��w�m
�l3�ʗ.�H��7���/!P�#�'͠��*��-�4�ꮬj{����ٻ�*�E��J�A�����&��ǉ��Ⴌ���[���,�TY�{~��lA�q�.�&�'KG�J��|p�ʌ��S���;�R�G�QvE�j��9�Ƈ8��8�Vf��r�cUa�'@s]c;����c	�0J���-�s��d-kO>(��/S��Ghae�*'���(A����8y��F/�]/��Ī�'��<B�r5;7��ʞ�4LR��T�D��:B�'���� ��e� a�}6>�g�r{ʭ�B27K<��갱�\���;�!�T΂R}@ᕜ�G��u5<xff�%4���"-��-�����T5�}�u��a���pe�]����Я���m��+D&���Z����u�O�?EeS��4��Ps��6���1�]�\��|�C6������͋m2�Y��W~��[��KU^c~s�˲���j�u�.�䟢�z���P��ݩ��?�HOp'C��N݌L�v8~����i7���ݮ�czw_��v�$����Ka�Ν|�ʯ�E(�&�����8C���g�yBY)#�Q�)5��fP���b<��-A�2P^D8�sW�������t��v3P�u��]V�=��_�&���o�n�uoS�6fδFUz)�SF#4��:���]���ɺ3C�����H�(�X-��(��r_�*x��q����d�6�ax\r��{�^��g���V#W��=�Q��w�� <��a^���mOe.�5������,���jNX��4�̃fe�>�->�`�M��L�N�����X,W�5XY�� ��(���`���B#a�F�`tvhU���N-��.���jh��4��@H��F�cӪ��*������-;AO1CS��D��붠�ؒz�������׺J!L�-�
�[y��B`1N)�J�\.�hL��@.�D��M��yx23�{�>&]�	i�ʂ�f.9u�*o��4������[�v[�{i�����Q�ڥ�z�Z@ioz���=I6���ؖZ���*����{�_n�ק������������k)�i���˙DU�\�Ѿ���,�����O��|�k&�^����0��)4���DxGs)�����`24��OL.bI?4�2]$�Cekp�tA�Q�Η��R͗�WHj�Ǝ��]�+{���u��hT+�#E��{T�'�Q����ע4n��:
��Y	hܢ�N'�.�5nᕾ;#�U��\W\ G��PYG�_�&Ӱ�h�f�^t�@9���Κ�#�0ܭ]�$��sg��q��e�n~
�WI&0���8����o�&�^y��r����
�����]�^c�z~��'ၠ	1(BS�w����@Ըzg�`o�Q�^�Q!}��i\�}E	�x(�$"x\�B��j���wpveA�?ưҮ���P)��2�<cݩ�M������8�L	l��N��@U�_A�n.����Zc4^�`�X���
f-4�e69���ƍy6����R��RI �-�5v�O��Y�i<�+hgd��v���gD��gh&p
����*oM�*!/R��W��z��u�������W��N�;��,� ��A���u��hx�y|�b�N�oZ�H�r� ЭS��Yi�DK��Y�1~�w�y�����/G����mWn#����W��y����i�q�аe��F��c�g��̤��+X��U֢!��ڋ���+�1���4Fd��_�g��(��O��=W���w���ͧ	4e��:W���3t~�K_4ʚJM��O��1_\E��\�����5M��    �޵(x����1�=Wz��0͟��a��\��3tΪŗ��Z���)UgtT{֌B3XC��&uR]� D�b��8,|��?�i��_ބ�-���_����$�=#�,Zp�0��^`\�h�G�ЗC�������Ƥ�;����A8ƹ�h���D\v�+0���]�D���ti�DQ�����?x��깻��$��閠S
cW(7��^5��ZT>9�u-��M�����d���5}
�D_���%�R�8����O?&��i&�v
?�LI.*�x~�+�r��8�n��C�vϏ����>y��n|4��vxz�!^Wq�y�?�v��5��������	a%�S����������?>���~���?��s��_��a�)�M�,�z��Y>��&��1�N�.qw2������
F�S�	����}ft?��<wwF�'JP�~��0��^;�'����f|�cx���B}е8ƔA22�u΂cL}�ρٕ���L�F�4��?�����o���/�}�<�_����o�y��ͯ�|�@�[����8e���ȎH��#J�½~��̯���S_��h��)���0V��
�JM$чy�3l�/�\����s��5�┅w:sי��!K� ���g���LP��8�捱�/���g�|�32Ƣ�,47�>$��5�3Jvj�Z�%�p������[�F���sҽp"�ܧa��_YxA�\�?D�钢���8c�y���T<U���8I�j��>l�.�\4f����k,���Qe�{��h9�b��͈=�A^0�bp�l8���ǻo�k�+�H_A�9�0Z,���k������w�����>W��y����+0�9�n����	]3��� ���O��%�G��6���t�[8��p�ib;vB��8ٳ�F݆���Bo�0�yW��p�/	�O!�� +=��]髱�C��x#+��iU��)�񶝲�P��H/gc��=M��6�%��WYX�K��S�L~��S��+��I�8n�xʛ�s#���G��J�(������鯐�4~
F�\L�f�+����H�V�ҳ*�O���;{�ɷ���g7 
�!R�sڷ��'-.o��ǹ���1M�:.?��ĝ�K��pi^��:��Jf�ƙ�+��!	��{|��⋭�b�8pr-v�wi�c�w;���2'���-���L�#~uV[�mdJ��������b�v�����T:���;��Ś�[/iZE�2�'YRl���+�:�MD����Ǉ�D(�%���2R��� ����}������)���ǌ��*�1U�+U8X��.W�1�c�+T�p�-�S������ ���Z�����u*�b��3�_rظ�x~�h����/�L.�(��jF���}k,��;c���q�3���Yg�{w�Cb�����'��q�J���J�EY�,�IL)��k?N��)��dq�8��g,T��[�JP	*���٧ո2x~K�Rj���584�� cΕOlQ��5I�/�D���3--��PH2SDF󼲬<�;��� V2K�0z�+�w�LY��TG��g�ڌ|�qye�S�����.�����|G;�໦ĵ(%=����L"\��}>Q��1	�H��\l#T4�FK\Ѹ�b�{���8��&��Bc)'W.,����2�`PX9} �EH{���p����0s������5��f 7؋D#����ǖ1�X�%���1�덶A5��%���q:A�����,뿎ʢUNY�R�jO�WA�*#c$Tn1�6�>~���	:�8K!O����<1>r�_�1(��B�O�)�%4�4F,�_�P����t�")�f����+xT�J�g��3��jw4����Jm34VO�+:�`�sǀ��^[`�3)C�y��� ��Ɗ.2*6%�GYE��e��9��t'\市��5�fT��~%�u^GU��LU(gɮ+=���?+��{���(K�ם*��[�3���� Hߎ�!h'k�0��a+/b�r���ɜڡi[~h0��Q��������D�
������	�7MԴ���߂�V�
�����V�-�f����y�Ї�`Ǧ�ݕ��Y�[M�����<O��1m][-�:{24f�����e�`]�*/��6���:8!�+jVx�Bc(�Gk������k�T�3y��Z�}���n�0R�b�T�i��fe�o�IRz"aPhv�^�V���;
��	<��Z��	-�	�MCD5ٌ��(����!���(��A3Fƍީpe�"�a^פ���A�(�:C�lV�o�eSӗ���(���-�
�7�!3K}rJU�K�J�fЌ'\�|���s�.j���}2J~B�
�	�g<��Řa�7Q�խOE���M��F����Oq��N�J�RDs�DЕIcYܗ��� m�����	��r��|ALa��S�SG�ؚ�2�)�W�q���W�7�N�������,�5�?�����)��j�]��	ʝ���vΰ�]:��/����qa���𞁂2����z����ʹ��Ih���R�m.�F~,�郋�y���o�c�9^�ND���V��z��aArzAIT-hcdh��a	S�r����c�v��V��^�F�S%�6Y^2B���N0��}<�@zy��H�����<�vdA�&C�T���₲l������
�/>^�L�w_�JV:�=X:s�+V�F�_��؋�<�(�y�z�e���$S�o�B0:�w�l�;ƫ^�M�����v�����S+�q���׋"c2p�Ilf1��T��u�_].�d6���F�(tl	�?R���Ğ�m�@���@E�@�rB��O�C{�!�J!֢H�q�ܧ�5)<l"[��!�������"6;�t�T�� akϑ
mg�}���(�Y�^�����A ڞ�R�f\�������s�K,ިg��)뵣M�-#v�mi���yN��!��1��S0��"-� Ô��"�z�%�c����먗O��/��Gj�f��WRZ�S���z��b��z%e��ȅ� s.�X|ٛ9ɵ%b<�+2(�-��Aդ���ç��_ζ�;�,�^����r�	�/�[FJy�W�qz!@�U�\o/@�����([�G�2A�G9+�c�ט�22�+�,�\�3�~�^�Pw���k�(M����yi�I�`M��pb�Ν�o�&��@q2��c#�J��:vb/v�m�����n��#��@e�ۯ�P�4�> ���^�B���3z�k#p1�C�S�,��G��jK)�YG�����y]7��B�:�m^�P.�8Ѐ%gh�����;�&��#p��S����E�N�o��1%����8��Ծx�E�)6y>�&Fy%� J��S�������1B�ܲC1��@�1�d(S�F���+�8�͟��2�Bgj]ӑ�4�-/:|.�A�;$|ZПL�XJv�y�c�����<��t���3��'JRf����O�3���Z�N��&XƐ���9�O(@j���5��'�5���O�3����7C;�?WNp��~��x�ק�o�gh����|-�@S�grP�������'�|y��E����\��7�r�i�9�P^F�H���sK�)L��Rbp� ��Pv�9iq�B�=Cc��ב"pH�@u��?�L }Ҽ�v��O`)���+)M���~WW��N������U��z,]��G9�t�u���k��nw\�:J릸�h�{���{jr:�R�"c�}]�A)���_#��<��T�?�m�h!
��c�h3)E팒��Y�`��gH9��<�P������ۊ�y��p�@6q���."̠�L��<�Rz�1N�%d�k�?�M���p�:N�T�s�h��_{�P`:wLQ�'���'U�ڭg�8�%��~Md�׵�K0��uy����=w�s6�z|�@8O����v�Z�Cy����e����+tk���5�3��ȗs_Ԓ:�3t�>M��I(,�>��,,�#�(��EwB�!��1�    bn{��U��b���VH Қq���e�?�);�?�>��".="��a������O�H<l�=(��*�\Js�ٹ\��}�ZH9z�G.P�<M�S����4�}��w~Q�͌/��K� �I�˽���.	�A���+,�T�#��,@�,]F�[����`.y"�:?��8ǩ�
�7?0`�x_��`a�HS��}
�d���z#%�C��K���I��:�Q(��6]TG�S��.���ӹ�h�ũnL��/}U�{c�� 3�N.��1��K^�4��wT^*%F��\�6��Z��0^�B�KVr�*Q�u���)g�Yw	ҳ�c(����m`<��1�n����i�S��9��6ʹ��t��5��%Acm��uܷ� 2���p�f���w/��]��=���tý9�#�xU7Z�B�f���z��<nd9Σ��?���%}
�M�E�Rxv��'W�2>f8�k�����o����q]Kҽc�~ݺg�oXұSҬ釚~����OoG+<��%C���r��Zc��#V�,�n��:��Y>���	T�՚���ĩ��1���)����"_k!�'��W[��#��_ßqy����RN�|Q�����%j�%��=�=�	\�7�u��v����)������3BF�����fh����az�`�(�c.U������E�VbS4�HS ��RD�}S/ɅpT1Cg���SE-���M���n�M��r�rq�%�v��r=Z� ��rK��,α�D	ZG��-H�d��1���ж���_{wj��PV�w���C,�.�K�-l�^@3��3-)����LO�F�x�L<a���ut���I��8/l�O���F�)v�I�a�.&�3Ď�@��k�\˧`��Ι���:��?��i)S�3�|t��W��d��u~������zH�W��� ���Ќ-c������`����q����S0���J�h�O>4�8M� S��=پ��UUd��q�`Nf��GK�Ơ ��+��>굽B�9������_{�e:���>s��CO$Ws��4�����xj��HoX2XB����S�f���/K�B�>C�Oj� �e��n�&|�M0:�"u�MY9�����9�B�|
z[��+'�*���K�N�����d��G������ ���w��5_(�6l� Od3J��W���D���"���-��K���!#?ث�ַ�q2��!Fj3�<k �ϥ|��
h����!�KG/a�a�I�22��,���V*Q�/��=dչB��=�T��<|�3�?���\>}�����U����D�a���)K)EA_���)�m�P+�|B�š
�5"c$���Q�B�_�?y7:��{O�FH �(c���M�zq��O ���|!�']R�m�K��z�F��!O�6!Iʝ(�$�8`���GC>پ�Y7��o����Ti�K{�;��?O��\@3�i����h���P?L���C}G�J��uF� �� u��w#_�-�7��.78ș���Hi�Ǜ�ݰ��0�� �V���1v^JZ��!26k����T{���������F-�2:�݂�������TT:�kz(�rX��	R=8~	F�X$5��=�{������<��!�Thߋ8�0�s�N����`�C��c)#�w6ѥ�b�ɘ��R(���}e�
� ��4�:)#[�%�}뿔T�o~�GW^aP?�+� �����W�������Zh�}�O�m;�/C��������{]x~xFh���Ͽ���~���?���������E���f��K�n1^�f��%T�;&�ʳ���T3��v���M	r���<ύ+MV�6m��@�{���	�5�4��`�'栅~�WVx#���ku��m��:������'�Q��xh�ۗh�G|�i�nJz|;z,`C�u[j������HT��[��)>j�L%���J�|AS�n�`�ukn��ο���8L����V� [>���U��;��Y�#.%��2L�6Â]��qa
�2T�7 wIĠ��ə)Ybdb��
��9F|�1H�f��H�tV �;ACc)o��)�_�N�kڞ�+,��$|G�;0J���L�Z��<c��1�J� Α�k�<��+��2TO� _|̐+����ѼOB+�W<���q��x|�^]�;[�X���������5/><������ڮo�S磖�8>��
cr�$d2�A��q��g`'a��<Jt���£��Oy'��r;��`N��,|t�W:%��W3X]�t��@���=�SZ��a�8A���m�I�l���{���:���p
1�0R����ޤe���\�KϤw�(Ue�ʏ�*L�t��%�'g�8�MT��m���O�o����}d���3X�=��P��rdDYd_@3�Զ�Qf�u0��@n#ZƂ���H����Q�w�׆��2w��][��
_�6�X�ݩOU�X���*�0�n��yµEL�ǩ~�Mx�,$�kc�,�r��s7?l6��˙_�����K�A�s��+���(�e�#����\u`��r�
7K^�l��80̄�2�y��HQˀ��NNU���Iae���3VYn69%��ǳ������]P\����z����=���.`���T��P(1� I�0j�͎��U��5A��{8MG�ڦ7�Ჽ����k;����!�'��K�b]o�y1QA��	����ķ�vv4(Z��Q����;�
1����A�;�l��3��۞�AD7��,<g'c�T߻��-�z������6������C	mʂ�8PA�5�QF�S����֒�)&��L�i��9M�ykW逤�1�5S��6瀦�(�8�U�怩dx���jF�$a�b���?�O�4�fK���JB��WR�V
T%b�"���D�(3:��[ۜxIp��|�T��@Q&�Vs�yŋ��2ã���K�G�ب���怗��vl|w(r遡$��Jl5m�J��9;]�怢�ب�_��&��B�\.r�����M?�֒�$���R�L(�KF�ٳ�e��$,[�*�o:�P�����v0�$*�Ɩkw,&J�Q�T~t}r8U�Z��@�������+�.���{���!�a+�e>�@��Yr�|l���v�$�̈́20�I�$��'7��|�G�a��͜�_��;�OO�˳����d&�ӿ�/�m� ����:��@<?���PO��4N�e����꽅0?/�h5��A���VY��6`�1ASVZ�b�D��	����@N���6��p�}�b�SªYʮd��@w"��ѧʶ� D%v��8m�Bf�x���4'a��ײE�7��<'�ٰE`5	���TM[r3!�
��w�hN���$h�u ��j��������I�1�sX�'�5�q ���)�bU���kD]����d7n��ƦS��[,yN�x_S,�MB�+o�j�%��5��*�bIn&��6^�u����X���ݱ2ƾhf�����zS�ijMc<#ߚ�[�8	%el\_QS�}��8e���hʨ�QR�:��e�ݧf��r���)CT^�\1��e5
����r'�L��@ٛ*���MSܠ�1B�O�l�@�ic����-����G�w�m��G�9�Y��@2JV'�Z3Ĩ�����h��(F��qz�U��j�w�nu����X�b�vNQ$�du�xiĎ�� ���)#n)��e/{SRץ
��em�زR�Cܓ�׷܉�-cJ��T�<A_4C����U���@U�u)�$ķ[Ӂ
y�j�]AΡ��ȹ�N�8�ޔIV�����8k��l^�u2؞�o&%�.�J�#݈�A��?a����nr@�s��waRRh��h��i2������O��(�Ք��^��(5�+��BH�"�A:�;]|�8PV�.t�hόy���,���-�6����֓��;ٿJ(�d�v�U�;��7 U1H��Ӵ��[����{f̌�g}�^ ;���F��W/p��rM�<^`v�    ���3�d���A��Ǵ'�O�Q��~Gw�>�W��е/m��ۗ��<����>��^/���}I Ĩ�� R�KF�uS��%�IP{�w�U/n�}"ı�4���n�,IO�+7�owu�HJ��Q*��U�EĒ$�C����2�<��(�+�{�f�nx4��Cg};ϯ�c�ں'���dGo�xe$	%w�P[]^p��{@}�Os;X�3�A��\�4���S��?nq/X ?	��ۻ�]]9X��(u� U��X��*����u������Z�;za��0�4����NY��>�k<o�k���x: ?	��M�+G�xOF�;�_� ����uͨ,+x'	|'���q���I(Ot�� �I��y5���c��d�%���$��q���P�ܕ��z�>	��<SM'����Iᄀ����~`�v��ЄYu�}m�o`"�*�Ȩj��!�z ��
L(�T��`B�d��"��Y:��P��V��u>h "��*?���`A"c!o�@�ʎ�PDh�{��.ܗ"�0S�3w���Q���ꄀe���턀%��1eY;��$Bv8���oo?�0^�.4<>O���{~�/�ƺ�G?��/��K;�c�"��i5g�o���(�Xr;j��C"cIzN�9QY���t'�&|���6����X���TS��e.�J���;�Ͱ8��&A��o���%�q����y���5�d��=˺�thK��!j����g�����f���m��ܼP�k�ǯj�Ej<�%�I���>���:C[���Tsf���)�M��Y���uJv��;��디�Q6�iηܗ��3���Kw�u��$����5M� �����W5�uDM1�^� ׉C�M�t�#4@rRw�K
�&A�|�\�� ����
7���ƛ��K�_�M�u2`�^ܝx���$���;��,�M�G�q���;X�N!D��As����}�	��v��7<>Oݣ}uƼ��?����=�ޛ�]wcK������J.��)��sG���2�g��i�\I|eo�K����f��Tw�f��g���^_M�����t͠��pT�.3R͕`U��Xt���t��B��l��^��{Wd�2��WPi[��ڽ��S�56�s��O�2B:#,LΟ�iڣ$��<�̴5E���y�%m��A�$�פ�BS��NN�1��k�ĺ��2a�6rgA�}��twv;a��?A�	#aJ3���vY7ጔo Z�AP^(�A|H'b��|̥�pY Ȁ3��F��b\$�m3����Kr���I3D�vp�\�����(9&��'�u�*�d�.�пIL��U߶�п�d����p~.���Y�Z�ٶq��uM�{̶�{���V�P?l@<�eE|[[;���s��5���ŵ��`:S|Ëg-�������ɸ�
o��Yk7���s�?�����ݷ���������|e����	�&"t�b3�w���Rc�BÉ�p�@d�S;4cK�^�{`8	0��8=К�RSm��u��$\�·5�L�f�I>�0����јv�D��7�{W����&W����Z!�\�\>�7-�\I\"��qĒo�J����Mn(9J��W
�Mn(�I����Mn(I���0��+�eIN�9������b��4�t�����D�%�Wv2����-��	_h>��ٓ���ғ�-pO�,�|a#B>4{��7�Y +J�b��	���貇"��N�|n#P�_�W���0�6�YA���vTm"`D:�E�M:
�;B��	n��gxtA���B�ܓO+a�� 8�l� /۠� E�t^���B���O�Dwl���-�X��
?&��It!�L�p�c�0���+n'�B�<^*��-T��S�VsOE�m�l-���h:3<�//ݔ��>����2�c�p���B��!j*n���z������*o˻�͍G��`8Cլ]T����prV��t3�8s0�~>�V���c���?C;�.!�'��:e7�(Fq8��	�/�+������VsK�Mo����NB�xg7��Ό�Ӝ���S�ی��4�SݳG�&��\�P�-0������i�8iM\����[�1���W��J>g�� Xsβ6�26NMh:����=�+�cF��N^)>:0�1>�L���<X��:JS@��0A�Y���4��12V�z��D=��1匌��3����6Vsz�6ز{�0k>��|�A��MW���T��F�9bT)��(��3�M\j�m� A=!�(gq����C������|3J�k�>@x�;A-i>�G��o��²rD�W��'����ў3s+NS����-�<�˘5%�k+�\o.e$�����'34������3X��`5��*�L7��Y�8�1�ʶ��\2����7��xf��V��E�-��f`�MH�N!.�q�z�2���>+� �$���ڧZ���Σ��g�s,9�-�H�L���ms(��(������Lٟ�u>�-H`�5��ʐ����V�y,��|�w=�<����8@��8��n{mwS�H�#z����夁�׫���l��3XMu��N���%c�`�ւ3����g� ��%���X��Xq��v�Un��u~)p���:Cd�;�Q�8�b�m�Q���S:�!�8@(#�i<�8�{\���{��&� k�γ�#D�Pz�s,X��!kJ��>Ua*"��#Z),^X���Ɣ�`�� ��n��A�yF9�;B��ؙ�s��!� |��d��\y�D$՗Ʉ`�ff���}��	~h&�M��)m�J#Jʰ����~G�l�YF�\ϸ�L��'36ΰ�W�nN(��(5U�+ ��};<�3����W�J��؍�$�ƹ��mB�#]�jN(�QDF��A��2��s��=��y~��6W#0��Ls�r�k�cM��6���i�����w�z|~`��m@K>��g�4p�	e�+�T�l�a�c�lB�º��.@s�p���nD��n6'�^P2d�<��p2]�S�����c,{���AoqF6Pʬ����'�.4�0C�D�	"e=_>D�6cl�XJv�q��cDJa ��L�O�i��^� ���8�����oF��;Ox�41�F�I�w�r �X��=OpF	,�[9�<�9����k:�md�s,x�QR
���9h!�p
c�S���f���+�H�VˊR(�@}n�F�����h��&����	������v�*u	��H�s,K<K&��r�!# ;	%? ��T���qB	�4�qF�Z �f�F�T�g9���@�=��Fzc���f��nn5�Fʊ�4�l��\�x�qBDI7����['��c��3�y2����c`v%#�)��!��������{r�qA��}�:%��g?�������a�^B׽��k��v��m���`�~���*�	fY�K0U[>7�)큞��5����5�t.���,���^`�T����Š�9BŜ���Y��M�/hN\��:ރ&PpqP��|�P� �Dvx%�}��c�(�`iN �4>�l	���@�iBg��w6Ϲ��$�����A�LB�v���8ׂ�����'l͋��&hvL���@o,�Z6����3BM�Ú(���(��)�5��܇
<��&����n��ef�li҆
&�Ӝ��a�mq}3��A�/�-^������(�Pt3�f5#d+�����N�B��'�we���xɚ|M�x?��k �a��?J�����-6�����¡w��P«�ڿbx���Xz�q��&�t^h�8����F���{:N�6�xm,����	J8	��lx]R z8@6��&��7���*��h"B6�#��ݿ!�ڸ�� w�p�~Ў���q��_B�s�)2��Y��"���s��6�RB�4f���SN��2ꌐ��&m�j����ف,���^�^}F��"Bqp��y-���'qj��,�F� �$i2H�i��Ob~�ƋC���Sڪ)8|We�Jhu)�,�HE�)��    ��\v��}!7[Y�aޜ���k@�v�֩ݩ�ڔ�ڃ�\�ZrvF��E�t��w�S����?\��ۻ�q����CߙѾc������ʇ�ԔT�,0t��4J�i꾂��in��4�����S�� �+��~T�,뱊���Q
����ku�knq�@ty������Yr�aΘ��B��v	Ŝ�RW��]
� Bd�<k�1��l"=�brof	j�	���te����JƓ����3D��_�v	�a<�Cc��KU==�s}l{J>w��cM����k��=	���d�x	dg8ٮ�=;>h�^Z`?3�X�üyiAGsA�X1(�	��L(�b��.��P��%3J�2{��
�c�j�NI@_2a欎l^ZЗ�(��A:|!v����f	��wT�9�璜�ΐ*^H\�0ǹ����9��ɶ͠9 ���}��оtnxl�3�>��`�^���]���V������C<ߎ�n*)��Ve�M��Z]���Aݣ煮m����ם��t����ﾽ�^�h4�Aoq+q���Lymc,��B�8�m�;��6YH���π�l2(B���� �Ǟ�k���Gm_����D^M�ЇW�:e�>8����K�] �3C4�`o�<�	@y2��3_u�� <��t�;�nu���=3��|컯�xO��舶�%P��p4�z�E$�c��;��[� K���I�#��K��I(��hy�N�A3DF����菛~7Ny{�}�%𠄙5��OF�j���ޓP�A]��f	|ǟ�}���H�ml�;(�*y�7�@zf���a*�bQ?A��y��͋T|�\�bz����<~�0у�	q+|�W-�p�Gv����q!���ΘM����
�N�mƎ�í=� F�E���`�L��F��L~GS��6Dȫ�h�#eV|��!�H�5��>V0�>��AsW��+��HK��dҶ@J�B�*�����6=#���F�@2�@�TnfJ���FtN��<]����P�����H`��Cɉ&+3�=�{�;�}wR�=���NH<�H{Bych�>@��)����\� ���K��z)K��0���:SȕF�k;gό����P��C�w x��A2B��m��֜I2�?6r���>��(SU�	%���aӹ�M�
U�0�r���0�e�ͶeY�t�1�ќl���>z7�L�Ǉ�g�?>M&�܋y�|�/�۶t5��(���vv�޶�ϡŸ9Ro���D���{j�0���lӔ�kƊo�i'��LJ�޷��b�ߔ�"���27w0�2ߍ؈Δ�z��'G�)�jWFuSf��f�[���>�SldT�;!�Gl�on{@2�)k�8�u�����$�#UP(�ʌ�h��ep8J¦�$��c��Ô�1Hl-�)�U$�O2-0���,Vv�K�Gu?7lxJ¦�n�־�9�0�V.U�sa���VXM�㚶'�Osq����OfLG��O�mӵGx��9��D��*Z_N'0�����	<%b먪*"�t%G�6>�qVw�����k7�=��^�'�h^m�:����y޾��eb�6\�3�Aw��6��\��ءn�\�_"T�h.�ҽ<ɕd&cuۛUn���6D���AY�+YNB��hs����KN���UވW� K:����o�KR�%+Ht>�`� d&�Y#p�֫?׿/�0[FT'��-0z�c�� *O����s�j�иfP���
��	�,hW9F�dqF��t��]�d��?H�d���"�.�� 	���*Am��ό���nإ�v������v)�;Ll���J�+�`��x�6H���(Y�`����
k$�;s2��ʊw(A�8avʂ�����ǱK�-�5���첤9476NY��>%g�����Yҁ������,�꛹k�'r��F���Ix�1+�W���qfhڂ�K� ��UΌ��l��p��&�p��9he�ʲo�)<�w.���^[T�A�g�h�Ƙ�@�%��(k�V�5��р=F��9>R6�7\� ���qB�� #��L�@yqA�x�k�
2P�����&3f�6����H�!g]Atrax�ww�����dqY�������~�"!�$�G�َ�B�g.�1iq[� ɓ-� awJFIY�Z`��jgQ±�M5h�|g��}�y�M��?uϝyh��^^^�0������.�n��85��ù����]p��������wcg�p3Qs���m����B��3RM	�*7v��oࣻ��qc!�|��1
��l�bBU���������Q��tU]! ���ί��Ƶ3>���2E��	'36nP���e�@Xg�^��S� bqy���0@��D||\j��V`3�6��O�V�4��C��T�63>�{���f&���5\�8�k���Ѵc�HM�)�X��
�&B�D���lf��n��f�Y�^s��}�C�&!�|�\���$�|e�#J��х����L���[��~�-0���ӝ�Z 7	����Fh%'�7#����o�*?t����|d\��0�	�kƎ����'�{�9�pWI�3��*D��OS�F��B��o���$=$C\:h���	R��CZwJ�l`��X���p��w�s7g�c{VӴ��\�u���@�� \�$ H5��j���prfJ��&w�5Ç�:�;AZ�Մ�ůve���Y�x>[�0P�%�̀�
���/�@��G����/{���\�u�}x��$��K%e��^'=�÷��C�d4.6�GM鸚�6@4t'74��rSM|#%���+ l��fI���+��0�5>��!`r:#�Tn�Kc<Lg�t�f#<�FX��|��=�gF|AW��2
+��bC��n[�������� �	��5�UT�K*�WD�c��Jl��M�m&�N���s �^�� c �pm8HW34�/���I�)��o�C�g�b�8>��Æ2����'����s��{���b_nv���{��gD:���������w�SaІ�׽1�!�Ğ7�3��@;a�&���\�!,4�bk��~_z�B�mn��E��X�AU<A�ˆ�~pIxr����n?X�����l@�g�9��0НeqG�C���_�O0�y;������ݨ�ڧ��{�f��<�@�t���qD:
W\Q�2y:�8ab w� �D-�����I;�v��f��~�*�t�'`��
z��CEOF(z�=:��N�n�HN[�0��M�b�v=_�(sK�b�D�rϕ&���]���"�c)�>�����P��hX?�V}A�<��)�m�"�D/��t�yKiR64��Jޚ���M���0�&>��1],(�|´��� ɓ!ҝ�:
�pr�5�͔�&_~0��4l�H����2�K<������vU%���w"�I���@�~S~��/��d˗�#tg���J-Btt׶��S�+Tl��f��k��X]��������O��y�����g���Оput�]�H� :��O��<<���Z���QH�Rp�3ҁ󕁜�
�&Btt� �B"�MG�g�+�XQ�s�E��r���F{/�%@r>GFN�y�4
�_�ҡPX�&(l����y��X{(�i�nt����.c����=�;����2�͝�$˩qn����>�j+@_�S�)���y�h���?h�����5������px]��֦8�#]S��ϸ:�*G�T��jO�_�OZ�A�N��ߎ��L3ұ25}�B�p٦��e5�&���@Բ? ������D�70?`2	h�)����b�J2D�ÔM�v$|c���{=(L4O��mG�/�Ҁ�LL�MhMFHwU7L�L�W{��ݦdf8�]�j�V��C��H���
M#n�!%�I�F�#\!�>M��،�f�d�2R>7*�P���c6�Nd�2��-�w!0�!V���,�I)��ʀ}�[�>H��D\=]���1�������J�[s&JY���^0���6����7�8�V4    ��J�mLҎεn� �� <H�[�">�8�6ۥ�)��	p����	��&��mLm��ov��=�k�����&���ԭ�W����4����+8�����wsA�-"������-(�%�q�9�M�jnb��F�vF\�d�t�.��e�9�A�.<�_��a/d��n������!��ۋ���2#��ݾ�B������%��1�o0`�i|�Jl1MHA�2� KL�t���'g����	�f�G�k��͌��p}3a��R[&�8�v�J���f.^�^��D�+�KՊXX�õ}tR�b�X{����Rrk����f�IJn'��o��q�g��n�Wj\�R!P���҇\}1t}7����V�TW��sY��q!^� �K�G>�<IJ#)��-S�vo��7����ܩ'�6F3��·�J|�y6���yG���<>���/����
��	�q�Qw��mp���u��unw��
"��]pZM�����	�,�9��7g���4����r�w�!j�*v��E|VS1�6�V�b(��\�!dBc4�5��]�;�����=���@|O]\���@|O���Baw�?'\}c�b�{k�;��&�3����$X�ҼU��K§�F�6�$�`��ި)�U/�g��ŝ���A�K�HG�d��`>�)�n�mb:;jN�W�teHLt�Q�'8�.h��P��Os������ jv̪�Д��}cz:\��I����z�}/��d�tqNv-xJ�g�pmw�Ʉ+4���)؝���i�_W��@V2>����+(K����U�� �O���绚��I�|��P�����]�|!}}/��RB;�K�z��2�@ł��8�?��2�6��W��4�P���kn��az����ƝFA�Ic��+�/C�܏Z�躒E��Ѩ1|�6��+C���?�ʚ��\��9�Z��Jǒ!j�r�8����#.������ʙ�F��ۙN*�ń�(�S�EVm��흤�I�!�I^�'��]܌^��D���'�䵂/��>���
�����6��ě��(�"Ď���2��õg�����.H ̵J�ge�H
�W>���K��(�W��T���)}�_�j1�ni�\h:�@nW2�T�F:|��.f����te�"����U��+�'u=Md�}��2D��tQ	-}�k�r��\�f��õ�^^��^��ڧ�����{����ˣ����qi_�̄OU��6��?��KzM�s�1t_�섳��z_�0��-�a�6��0їE��k�����a��D��)Ә�����B���[���L�"��q^1����)�P�������R0� j�"j\�Q��&\����G7
�=B�%�pt�8���s��q�������C໚;����>.�l��,A�?Hat�G�'d��4FS��.nj$_:!�c�UR����l\P!K��Ź�����ՆAȐ\h�⻐�Br�qad~&d�f3�
��^䝚���#*��>�S���'(1�8�B��	�e����,�<D��� �P`��g*�W7%�tf�u3;���Iw��H�K��@���ߍĉ�[�Ep'�qB��"��\��?:r]�4W;�>:��	��>�*G3U955%jx�QsȬ&Y=oO�mƑ�������ӏo1��"Z״���5��2.O6ni��F|C�k��V�Wz��D9P}��ODwEצ�Q�?Y׌|u���+'���R��+�˄+��`�w���(����cx�0J��8A�]6jd�\H��'�wQ��`w�RR:ˆ�E���O�|���o�(4R"F��Z$��Υ%:\�J`{sq�2g�o���rq� ���Ky��}	m�sB���Z<��|����EU�22d�tU��B�0g�^SĦ�����i>��	��KvK�T ���д���FN�{��ϵ��AA0�&�m��Rn"���0��4G�����N�elߴӯ5���yx���3�ut�q��U����y�>��@�-!Swnh�W�����>��@�;�l���pn>��@�.�T,V��%������+�F&���˰4����v0(�y�����/��O��f{�[#+�[IM&D�7�'��{9h�e��)k5[Y����Ά�L�5�m�d#����\�؄x=a��	�Q���0��F&=Z�`�F�Nf⎚��olh����AS��i%�k�,4Wd�,$�ӬQ���d�������=(F�c����v�&8l��4�V7���c��4ˬu�3�4&dAs��M�s�]m�0���5�i*�2��K�T�d��DP���&��t7�4H�ep�J��u�_���ڶ'ӝڱ��5�/��D�4^SƠ���R��KX�ߴ�/Uݾ�T�*�T�8��j@�e���{��aP�I�4��n���"�#* ��1�g*��啠����S��Ǘt�aL��@�_�?3�'MVdJ�,���U�/Ug�!���C�5pa����_
~	��n��~-��R�pA��)61�=�1�U���)�޴��,,����llcY#���Y�X�E�ͫyxncYꈈ�6"�Q �1_��7FT��v�T�#��M��ԁgdpd�Pp��722���¼�]L�l3j*�TfP}$c�����-c�[�QT��o���V�V�^Y����	$bd�.NMb q�ٰ0���5�H�H?�_ͨ�����o�~$XD�6."�����oFh�#�#���G4�9��P�j�f������ih�85`	QG�p��Ls�O�8f�z��`�)�_!��3��U��ݽ�="8�y��Q:L	��~m�o�`=a4l�<=+���˔N"F��`�����;ڷH�/2�l�L^D�0�#!�����p�����сC���1:o\D��QzLS�0/Y���0Q
��<$�3F�6����yݭη�"(�xr�����y�<,&���[(���� �N�W�`��<"8�������]׌����ł��#F�������c�54Q\I�ȍq$p˔�Ap���}���-�~Gąח��b���dy�xZ���<pߕ>õ'�Vs�Q�4�+��l�8=�E��}�Jg��Տ�t#2&�(ı�L2gH�Z��f�T��0#J^��0��D��
�38"�X��e��e����I��	��8a�
wdSD�?�گmg����
�#���v�L����u�%*���4�{%�2A����g�JJ������^jE�}����1v]dB��E|&�%����x!2.����׌5n�����D7q�l%\�XbdRe�՝����pzB�Ǹf��	�+�R%&�3�E��8fpL3�EQ�d��,̦	'W_:���Mn}\}�U"-U|��f�d9"QdgNah���v�Ԛ�Yz�Bwn���n�Q*����66n�3{
��L�AJ�iSEg3F��XǤ�P�h���x	�163�؝����?Ȧ[d",�����_P��3�#�q	�$�fE�;6o!�#/���Gq�1f*Et��2�������@�u!RDPf��l���@�]{�CӲ�F8��
}?_KO�tn +Ù���W;�r �����q&ŀ��28&q�U@/"���,s���vds ����~��sTc{T!�vz<�l"�_0H����&��u��d��|0�WI5S�aDNEt���r��dp�o�P^HQ�ڠ��}|�:���γ�F�*��?��l�
��ӗ����_���ڧ>�OO������5�/.�#Ŀ}���_��w�|�����������"D#C�|�~3��)�P�T�4�P���:�o����͠��h�������Tڼ4�C!���f	���u24�Y�Z�f���55�nQJ��m�9�6.�Q�z�j��Osh���ْ�Dh�i�k��ϖt&C�]�h�d1ƜB׌�0nh�W�ǧ�aӚ�0<��w?�N���ӹ�e����2�g|�u���;�FmIѨqnSQ[�sJ�4;�U�+�Crc�5�j�u5� �  s�����ǧ�~����a�Ǿ����qx��KVC��=��4a����7�(P����Z�p�prmc5��=6��:`230��2�����w�1y���7�#6��	�����%��Z�x*�/�P�W��L��� T'LT^C�0_�1��CY��A�ea@>ҩi>����6�pn�f)����똀u��×l*arTg��=J>��i��+��/i�u'�c���`b��T�fs��&V����R����Pel����6a,�M�j�V�����K��$��X�� q�;��>�k�������q��N��a|xxy��//O���҂N�`6�S=���;_S@�N���ù]W�g����}�x>���v�z|��gpcK�l
'
/�R�X�b{<�{	�� �m��\,�\�M���R��w>W�3�y;��5��>��}�C�4���x&�+CC��[�T�6�vB`YA�ƹ���e���HU�kI�"�ЌN�������dpA����:v%oɸ4c`�[��%�뚶��'���:����_�x2�ɘ�;Ex߻�������	���_���?�������&����A����?���_~����~��o���凿�1�����b>��g���┉������~�����?>�����f�}�_��7�N1�=����Ǉ?���O����_~n���pX��a)�5����ӧ���߾�u6����M4�NĨ:���OȊ?}�F�v'��/���yL!lþ>����v��g5�2K�����{<��"�g���+���Ą�j��1������8B5�t�o=k��@�bF�9o������@ ��I�0�A ���]��A��3�{�����ڿ�:�I���@E"��3�A�n
pF�1��8�A (�=�D��o�/��/�KG��            x������ � �            x�3��r�v�LL�������� K �         R   x�KL����T1JT14P�ϨH�5O4����
v)�0(5��H*��O��wN7�2*p+0.�p	��-�,����� k d     