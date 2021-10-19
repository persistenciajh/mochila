PGDMP     :    2    	        	    y            mochila    9.3.25    9.3.25 l    3           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
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
    public       postgres    false    175   �                 0    16467 	   user_role 
   TABLE DATA               A   COPY public.user_role (user_role_id, role, username) FROM stdin;
    public       postgres    false    176   �                 0    16472    users 
   TABLE DATA               8   COPY public.users (username, clave, estado) FROM stdin;
    public       postgres    false    177   6�       �           2606    16402    con_pagos_pkey 
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
�@Eי��T�h�Kk����((���pZ> �E8�sZ��EK��C�a�rM:`�-��}q<�:�;v���S!���l����_�?% --��k`� �"��c���,�->�l�x��2��!��~ef9L��]k8�      *   O   x�34��4���443521 ��]Ad�#���k��g�c����cHh������������������rpp��qqq ���      .   B   x�3�v�	uQ�Tpr��q�r�t���4202�5��52W00�#�0L�i�eL�V�Z��=... C�v            x������ � �            x������ � �            x�3��r�v�LL�������� K �         R   x�KL����T1JT14P�ϨH�5O4����
v)�0(5��H*��O��wN7�2*p+0.�p	��-�,����� k d     