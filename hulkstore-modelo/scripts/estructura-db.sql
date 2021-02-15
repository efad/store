--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.9
-- Dumped by pg_dump version 12.2

-- Started on 2021-02-14 23:22:35

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2188 (class 1262 OID 74014)
-- Name: hulkstore; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE hulkstore WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Spain.1252' LC_CTYPE = 'Spanish_Spain.1252';


ALTER DATABASE hulkstore OWNER TO postgres;

\connect hulkstore

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

--
-- TOC entry 188 (class 1259 OID 74102)
-- Name: catalogo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.catalogo (
    cat_id integer NOT NULL,
    cat_alias character varying(500) NOT NULL,
    audi_usuario character varying(200) NOT NULL,
    audi_fecha date NOT NULL,
    audi_cliente character varying(200),
    cat_estado boolean
);


ALTER TABLE public.catalogo OWNER TO postgres;

--
-- TOC entry 2189 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN catalogo.cat_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.catalogo.cat_id IS 'Identificador de registro';


--
-- TOC entry 2190 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN catalogo.cat_alias; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.catalogo.cat_alias IS 'Alias único descriptivo del catálogo';


--
-- TOC entry 2191 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN catalogo.audi_usuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.catalogo.audi_usuario IS 'Usuario de auditoría';


--
-- TOC entry 2192 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN catalogo.audi_fecha; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.catalogo.audi_fecha IS 'Fecha de auditoría';


--
-- TOC entry 2193 (class 0 OID 0)
-- Dependencies: 188
-- Name: COLUMN catalogo.audi_cliente; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.catalogo.audi_cliente IS 'Cliente host';


--
-- TOC entry 190 (class 1259 OID 74113)
-- Name: catalogo_detalle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.catalogo_detalle (
    cat_det_id integer NOT NULL,
    cat_det_alias character varying(500) NOT NULL,
    cat_det_nombre character varying(500),
    cat_det_descripcion character varying(5000),
    cat_det_orden integer,
    audi_usuario character varying(200) NOT NULL,
    audi_fecha date NOT NULL,
    audi_cliente character varying(200),
    cat_det_estado boolean,
    cat_id integer NOT NULL
);


ALTER TABLE public.catalogo_detalle OWNER TO postgres;

--
-- TOC entry 2194 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN catalogo_detalle.cat_det_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.catalogo_detalle.cat_det_id IS 'Identificador de registro';


--
-- TOC entry 2195 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN catalogo_detalle.cat_det_alias; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.catalogo_detalle.cat_det_alias IS 'Alias único descriptivo del detalle del catálogo';


--
-- TOC entry 2196 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN catalogo_detalle.cat_det_nombre; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.catalogo_detalle.cat_det_nombre IS 'Nombre del detalle del catálogo';


--
-- TOC entry 2197 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN catalogo_detalle.cat_det_descripcion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.catalogo_detalle.cat_det_descripcion IS 'Descripción del detalle del catálogo';


--
-- TOC entry 2198 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN catalogo_detalle.cat_det_orden; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.catalogo_detalle.cat_det_orden IS 'Orden para las opciones';


--
-- TOC entry 2199 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN catalogo_detalle.audi_usuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.catalogo_detalle.audi_usuario IS 'Usuario de auditoría';


--
-- TOC entry 2200 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN catalogo_detalle.audi_fecha; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.catalogo_detalle.audi_fecha IS 'Fecha de auditoría';


--
-- TOC entry 2201 (class 0 OID 0)
-- Dependencies: 190
-- Name: COLUMN catalogo_detalle.audi_cliente; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.catalogo_detalle.audi_cliente IS 'Cliente host';


--
-- TOC entry 189 (class 1259 OID 74111)
-- Name: catalogo_detalle_cat_det_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.catalogo_detalle_cat_det_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.catalogo_detalle_cat_det_id_seq OWNER TO postgres;

--
-- TOC entry 2202 (class 0 OID 0)
-- Dependencies: 189
-- Name: catalogo_detalle_cat_det_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.catalogo_detalle_cat_det_id_seq OWNED BY public.catalogo_detalle.cat_det_id;


--
-- TOC entry 194 (class 1259 OID 74200)
-- Name: movimiento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movimiento (
    movi_id integer NOT NULL,
    prod_id integer NOT NULL,
    movi_cantidad integer NOT NULL,
    movi_tipo character varying(500) NOT NULL,
    movi_fecha date NOT NULL,
    movi_valor numeric(10,2) NOT NULL,
    audi_fecha date NOT NULL,
    audi_usuario character varying(200) NOT NULL,
    audi_cliente character varying(200),
    movi_estado boolean,
    usua_id integer
);


ALTER TABLE public.movimiento OWNER TO postgres;

--
-- TOC entry 2203 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN movimiento.movi_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.movimiento.movi_id IS 'Identificador de registro';


--
-- TOC entry 2204 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN movimiento.prod_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.movimiento.prod_id IS 'Identificador del registro foráneo de producto';


--
-- TOC entry 2205 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN movimiento.movi_cantidad; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.movimiento.movi_cantidad IS 'Cantidad de productos involucrados en el movimiento';


--
-- TOC entry 2206 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN movimiento.movi_tipo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.movimiento.movi_tipo IS 'Tipo de movimiento';


--
-- TOC entry 2207 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN movimiento.movi_fecha; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.movimiento.movi_fecha IS 'Fecha del movimiento';


--
-- TOC entry 2208 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN movimiento.movi_valor; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.movimiento.movi_valor IS 'Valor del movimiento';


--
-- TOC entry 2209 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN movimiento.audi_fecha; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.movimiento.audi_fecha IS 'Fecha de auditoría';


--
-- TOC entry 2210 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN movimiento.audi_usuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.movimiento.audi_usuario IS 'Usuario de auditoría';


--
-- TOC entry 2211 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN movimiento.audi_cliente; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.movimiento.audi_cliente IS 'Cliente host';


--
-- TOC entry 2212 (class 0 OID 0)
-- Dependencies: 194
-- Name: COLUMN movimiento.movi_estado; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.movimiento.movi_estado IS 'Estado del registro:  1 Activo,  0 Inactivo';


--
-- TOC entry 193 (class 1259 OID 74198)
-- Name: movimiento_movi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.movimiento_movi_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movimiento_movi_id_seq OWNER TO postgres;

--
-- TOC entry 2213 (class 0 OID 0)
-- Dependencies: 193
-- Name: movimiento_movi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.movimiento_movi_id_seq OWNED BY public.movimiento.movi_id;


--
-- TOC entry 192 (class 1259 OID 74189)
-- Name: producto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.producto (
    prod_id integer NOT NULL,
    prod_nombre character varying(500) NOT NULL,
    prod_descripcion character varying(5000),
    prod_valor numeric(10,2) NOT NULL,
    prod_existencia integer NOT NULL,
    audi_usuario character varying(200) NOT NULL,
    audi_fecha date NOT NULL,
    audi_cliente character varying(200),
    prod_estado boolean
);


ALTER TABLE public.producto OWNER TO postgres;

--
-- TOC entry 2214 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN producto.prod_nombre; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.producto.prod_nombre IS 'Nombre del producto';


--
-- TOC entry 2215 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN producto.prod_descripcion; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.producto.prod_descripcion IS 'Descripción del producto';


--
-- TOC entry 2216 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN producto.prod_valor; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.producto.prod_valor IS 'Valor del producto unitario';


--
-- TOC entry 2217 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN producto.prod_existencia; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.producto.prod_existencia IS 'Existencia en el almacen';


--
-- TOC entry 2218 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN producto.audi_usuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.producto.audi_usuario IS 'Usuario de auditoría';


--
-- TOC entry 2219 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN producto.audi_fecha; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.producto.audi_fecha IS 'Fecha de auditoría';


--
-- TOC entry 2220 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN producto.audi_cliente; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.producto.audi_cliente IS 'Cliente host';


--
-- TOC entry 2221 (class 0 OID 0)
-- Dependencies: 192
-- Name: COLUMN producto.prod_estado; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.producto.prod_estado IS 'Estado del registro:  1 Activo,  0 Inactivo';


--
-- TOC entry 191 (class 1259 OID 74187)
-- Name: producto_prod_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.producto_prod_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.producto_prod_id_seq OWNER TO postgres;

--
-- TOC entry 2222 (class 0 OID 0)
-- Dependencies: 191
-- Name: producto_prod_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.producto_prod_id_seq OWNED BY public.producto.prod_id;


--
-- TOC entry 187 (class 1259 OID 74100)
-- Name: tipo_movimiento_tipo_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tipo_movimiento_tipo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipo_movimiento_tipo_id_seq OWNER TO postgres;

--
-- TOC entry 2223 (class 0 OID 0)
-- Dependencies: 187
-- Name: tipo_movimiento_tipo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tipo_movimiento_tipo_id_seq OWNED BY public.catalogo.cat_id;


--
-- TOC entry 186 (class 1259 OID 74033)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    usua_id integer NOT NULL,
    usua_login character varying(500) NOT NULL,
    usua_contrasenia character varying(500) NOT NULL,
    audi_usuario character varying(200) NOT NULL,
    audi_fecha date NOT NULL,
    audi_cliente character varying(200) NOT NULL,
    usua_tipo character varying(50),
    usua_estado boolean,
    usua_nombre character varying(500),
    usua_email character varying(500),
    usua_identificacion character varying(15)
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 2224 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN usuario.usua_id; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario.usua_id IS 'Identificador de registro';


--
-- TOC entry 2225 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN usuario.usua_login; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario.usua_login IS 'Nombre del usuario';


--
-- TOC entry 2226 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN usuario.usua_contrasenia; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario.usua_contrasenia IS 'Contraseña del usuario encriptada';


--
-- TOC entry 2227 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN usuario.audi_usuario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario.audi_usuario IS 'Usuario de auditoría';


--
-- TOC entry 2228 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN usuario.audi_fecha; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario.audi_fecha IS 'Fecha de auditoría';


--
-- TOC entry 2229 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN usuario.audi_cliente; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario.audi_cliente IS 'Cliente host';


--
-- TOC entry 2230 (class 0 OID 0)
-- Dependencies: 186
-- Name: COLUMN usuario.usua_tipo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.usuario.usua_tipo IS 'Tipo de usuario: ADMINISTRADOR, CLIENTE';


--
-- TOC entry 185 (class 1259 OID 74031)
-- Name: usuario_usua_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_usua_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_usua_id_seq OWNER TO postgres;

--
-- TOC entry 2231 (class 0 OID 0)
-- Dependencies: 185
-- Name: usuario_usua_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_usua_id_seq OWNED BY public.usuario.usua_id;


--
-- TOC entry 2031 (class 2604 OID 74105)
-- Name: catalogo cat_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.catalogo ALTER COLUMN cat_id SET DEFAULT nextval('public.tipo_movimiento_tipo_id_seq'::regclass);


--
-- TOC entry 2032 (class 2604 OID 74116)
-- Name: catalogo_detalle cat_det_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.catalogo_detalle ALTER COLUMN cat_det_id SET DEFAULT nextval('public.catalogo_detalle_cat_det_id_seq'::regclass);


--
-- TOC entry 2034 (class 2604 OID 74203)
-- Name: movimiento movi_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento ALTER COLUMN movi_id SET DEFAULT nextval('public.movimiento_movi_id_seq'::regclass);


--
-- TOC entry 2033 (class 2604 OID 74192)
-- Name: producto prod_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producto ALTER COLUMN prod_id SET DEFAULT nextval('public.producto_prod_id_seq'::regclass);


--
-- TOC entry 2030 (class 2604 OID 74036)
-- Name: usuario usua_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN usua_id SET DEFAULT nextval('public.usuario_usua_id_seq'::regclass);


--
-- TOC entry 2176 (class 0 OID 74102)
-- Dependencies: 188
-- Data for Name: catalogo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.catalogo (cat_id, cat_alias, audi_usuario, audi_fecha, audi_cliente, cat_estado) VALUES (2, 'TIPO_ARTICULO', 'edison.agurto', '2021-02-14', '127.0.0.1', true);
INSERT INTO public.catalogo (cat_id, cat_alias, audi_usuario, audi_fecha, audi_cliente, cat_estado) VALUES (1, 'TIPO_MOVIMIENTO', 'edison.agurto', '2021-02-14', '127.0.0.1', true);


--
-- TOC entry 2178 (class 0 OID 74113)
-- Dependencies: 190
-- Data for Name: catalogo_detalle; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.catalogo_detalle (cat_det_id, cat_det_alias, cat_det_nombre, cat_det_descripcion, cat_det_orden, audi_usuario, audi_fecha, audi_cliente, cat_det_estado, cat_id) VALUES (1, 'ENTRADA', 'Entrada Producto', 'Corresponde al ingreso de mercadería al inventario', 0, 'edison.agurto', '2021-02-14', '127.0.0.1', true, 1);
INSERT INTO public.catalogo_detalle (cat_det_id, cat_det_alias, cat_det_nombre, cat_det_descripcion, cat_det_orden, audi_usuario, audi_fecha, audi_cliente, cat_det_estado, cat_id) VALUES (2, 'SALIDA', 'Salida Producto', 'Corresponde a la venta de mercadería al público', 1, 'edison.agurto', '2021-02-14', '127.0.0.1', true, 1);
INSERT INTO public.catalogo_detalle (cat_det_id, cat_det_alias, cat_det_nombre, cat_det_descripcion, cat_det_orden, audi_usuario, audi_fecha, audi_cliente, cat_det_estado, cat_id) VALUES (3, 'REPARACION', 'Reparación Producto', 'Corresponde a la reparación del producto', 2, 'edison.agurto', '2021-02-14', '127.0.0.1', true, 1);


--
-- TOC entry 2182 (class 0 OID 74200)
-- Dependencies: 194
-- Data for Name: movimiento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.movimiento (movi_id, prod_id, movi_cantidad, movi_tipo, movi_fecha, movi_valor, audi_fecha, audi_usuario, audi_cliente, movi_estado, usua_id) VALUES (8, 19, 10, 'ENTRADA', '2021-02-14', 100.15, '2021-02-14', 'edison.agurto', '127.0.0.1', true, 10);
INSERT INTO public.movimiento (movi_id, prod_id, movi_cantidad, movi_tipo, movi_fecha, movi_valor, audi_fecha, audi_usuario, audi_cliente, movi_estado, usua_id) VALUES (11, 19, 11, 'ENTRADA', '2021-02-14', 70.15, '2021-02-14', 'edison.agurto', '127.0.0.1', true, 10);
INSERT INTO public.movimiento (movi_id, prod_id, movi_cantidad, movi_tipo, movi_fecha, movi_valor, audi_fecha, audi_usuario, audi_cliente, movi_estado, usua_id) VALUES (15, 19, 11, 'ENTRADA', '2021-02-14', 100.15, '2021-02-14', 'edison.agurto', '127.0.0.1', true, 10);


--
-- TOC entry 2180 (class 0 OID 74189)
-- Dependencies: 192
-- Data for Name: producto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.producto (prod_id, prod_nombre, prod_descripcion, prod_valor, prod_existencia, audi_usuario, audi_fecha, audi_cliente, prod_estado) VALUES (19, 'Bati Taza Oficial', 'Batitaza negra de metal', 20.15, 15, 'edison.agurto', '2021-02-14', '127.0.0.1', true);
INSERT INTO public.producto (prod_id, prod_nombre, prod_descripcion, prod_valor, prod_existencia, audi_usuario, audi_fecha, audi_cliente, prod_estado) VALUES (23, 'Batitaza', 'Batitaza negra de metal', 40.40, 15, 'anonymous', '2021-02-14', '127.0.0.1', true);


--
-- TOC entry 2174 (class 0 OID 74033)
-- Dependencies: 186
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuario (usua_id, usua_login, usua_contrasenia, audi_usuario, audi_fecha, audi_cliente, usua_tipo, usua_estado, usua_nombre, usua_email, usua_identificacion) VALUES (8, 'gina.carano', '105c683d740ef420cd26e9d73b4252f171a8f4c1c3a600ce645fb8eaff31467f', 'enrique.bunbury', '2020-02-12', '192.1.1.1', 'PUBLICO', true, 'Gina Carano', NULL, NULL);
INSERT INTO public.usuario (usua_id, usua_login, usua_contrasenia, audi_usuario, audi_fecha, audi_cliente, usua_tipo, usua_estado, usua_nombre, usua_email, usua_identificacion) VALUES (9, 'bruce.banner', '60bcad77c43c4ab9840d8aa44b6c18e692e6c8e5e4f40351561c41274f1197c8', 'enrique.bunbury', '2020-02-12', '192.1.1.1', 'PUBLICO', true, 'Bruce Banner', NULL, NULL);
INSERT INTO public.usuario (usua_id, usua_login, usua_contrasenia, audi_usuario, audi_fecha, audi_cliente, usua_tipo, usua_estado, usua_nombre, usua_email, usua_identificacion) VALUES (11, 'carlos.quintana', '9bf5bb3e4bd40e48b2a893d8928a13c0289f9a74404437ffc44070135c95f290', 'edison.agurto', '2021-02-14', '127.0.0.1', 'PUBLICO', true, 'Carlos José Quintana', NULL, NULL);
INSERT INTO public.usuario (usua_id, usua_login, usua_contrasenia, audi_usuario, audi_fecha, audi_cliente, usua_tipo, usua_estado, usua_nombre, usua_email, usua_identificacion) VALUES (10, 'edison.agurto', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'edison.agurto', '2020-02-12', '192.1.1.1', 'ADMINISTRADOR', true, 'Edison Agurto', NULL, NULL);


--
-- TOC entry 2232 (class 0 OID 0)
-- Dependencies: 189
-- Name: catalogo_detalle_cat_det_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.catalogo_detalle_cat_det_id_seq', 23, true);


--
-- TOC entry 2233 (class 0 OID 0)
-- Dependencies: 193
-- Name: movimiento_movi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movimiento_movi_id_seq', 29, true);


--
-- TOC entry 2234 (class 0 OID 0)
-- Dependencies: 191
-- Name: producto_prod_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.producto_prod_id_seq', 43, true);


--
-- TOC entry 2235 (class 0 OID 0)
-- Dependencies: 187
-- Name: tipo_movimiento_tipo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tipo_movimiento_tipo_id_seq', 23, true);


--
-- TOC entry 2236 (class 0 OID 0)
-- Dependencies: 185
-- Name: usuario_usua_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_usua_id_seq', 29, true);


--
-- TOC entry 2038 (class 2606 OID 74130)
-- Name: catalogo cat_alias_unico; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.catalogo
    ADD CONSTRAINT cat_alias_unico UNIQUE (cat_alias);


--
-- TOC entry 2042 (class 2606 OID 74121)
-- Name: catalogo_detalle catalogo_detalle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.catalogo_detalle
    ADD CONSTRAINT catalogo_detalle_pkey PRIMARY KEY (cat_det_id);


--
-- TOC entry 2044 (class 2606 OID 74144)
-- Name: catalogo_detalle det_alias_unico; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.catalogo_detalle
    ADD CONSTRAINT det_alias_unico UNIQUE (cat_det_alias);


--
-- TOC entry 2052 (class 2606 OID 74208)
-- Name: movimiento movimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento
    ADD CONSTRAINT movimiento_pkey PRIMARY KEY (movi_id);


--
-- TOC entry 2047 (class 2606 OID 74197)
-- Name: producto producto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (prod_id);


--
-- TOC entry 2040 (class 2606 OID 74110)
-- Name: catalogo tipo_movimiento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.catalogo
    ADD CONSTRAINT tipo_movimiento_pkey PRIMARY KEY (cat_id);


--
-- TOC entry 2036 (class 2606 OID 74041)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (usua_id);


--
-- TOC entry 2045 (class 1259 OID 74237)
-- Name: fki_det_cat_id_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_det_cat_id_fk ON public.catalogo_detalle USING btree (cat_id);


--
-- TOC entry 2048 (class 1259 OID 74219)
-- Name: fki_mov_tipo_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_mov_tipo_fk ON public.movimiento USING btree (movi_tipo);


--
-- TOC entry 2049 (class 1259 OID 74221)
-- Name: fki_prod_id_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_prod_id_fk ON public.movimiento USING btree (prod_id);


--
-- TOC entry 2050 (class 1259 OID 74231)
-- Name: fki_usua_id_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_usua_id_fk ON public.movimiento USING btree (usua_id);


--
-- TOC entry 2053 (class 2606 OID 74232)
-- Name: catalogo_detalle det_cat_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.catalogo_detalle
    ADD CONSTRAINT det_cat_id_fk FOREIGN KEY (cat_id) REFERENCES public.catalogo(cat_id);


--
-- TOC entry 2054 (class 2606 OID 74209)
-- Name: movimiento mov_tipo_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento
    ADD CONSTRAINT mov_tipo_fk FOREIGN KEY (movi_tipo) REFERENCES public.catalogo_detalle(cat_det_alias);


--
-- TOC entry 2055 (class 2606 OID 74226)
-- Name: movimiento mov_usua_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movimiento
    ADD CONSTRAINT mov_usua_id_fk FOREIGN KEY (usua_id) REFERENCES public.usuario(usua_id);


-- Completed on 2021-02-14 23:22:35

--
-- PostgreSQL database dump complete
--

