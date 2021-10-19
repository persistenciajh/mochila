--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.25
-- Dumped by pg_dump version 9.3.25
-- Started on 2021-10-08 11:49:50

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 194 (class 1259 OID 24768)
-- Name: inv_producto; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.inv_producto (
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


ALTER TABLE public.inv_producto OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 24766)
-- Name: inv_producto_secuencia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.inv_producto_secuencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.inv_producto_secuencia_seq OWNER TO postgres;

--
-- TOC entry 2020 (class 0 OID 0)
-- Dependencies: 193
-- Name: inv_producto_secuencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.inv_producto_secuencia_seq OWNED BY public.inv_producto.secuencia;


--
-- TOC entry 1900 (class 2604 OID 24771)
-- Name: secuencia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inv_producto ALTER COLUMN secuencia SET DEFAULT nextval('public.inv_producto_secuencia_seq'::regclass);


--
-- TOC entry 2014 (class 0 OID 24768)
-- Dependencies: 194
-- Data for Name: inv_producto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inv_producto (secuencia, cantidad_min, codigo, descripcion, descuento_max, edad_consumidor, estado, fecha_actualizacion, fecha_creacion, genero_consumidor, nombre, precio_venta, ult_precio_compra, secuencia_tipo_producto, secuencia_usuario_actualizacion, secuencia_usuario_creacion) FROM stdin;
1	5	800	ENERGIZATE NATURAL CON ESTRACTO DE MALTA Y BOROJO 	0	\N	A	2021-09-27 00:00:00	2021-09-27 00:00:00	\N	ENERGIZATE NATURAL	25000	20000	3	1	1
2	5	801	SOMBRE SEVEN COOL 24 TONOS	0	\N	A	\N	2021-09-27 00:00:00	\N	SOMBRE SEVEN COOL 24 TONOS	21000	20000	2	\N	1
6	5	802	DESCRIPCION	0	\N	A	\N	2021-10-08 00:00:00	\N	NOMBRE	10000	10000	2	\N	1
\.


--
-- TOC entry 2021 (class 0 OID 0)
-- Dependencies: 193
-- Name: inv_producto_secuencia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.inv_producto_secuencia_seq', 6, true);


--
-- TOC entry 1902 (class 2606 OID 24776)
-- Name: inv_producto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.inv_producto
    ADD CONSTRAINT inv_producto_pkey PRIMARY KEY (secuencia);


-- Completed on 2021-10-08 11:49:51

--
-- PostgreSQL database dump complete
--

