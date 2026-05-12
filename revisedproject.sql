--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4


SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'SQL_ASCII';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: admin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE admin (
    aid integer NOT NULL,
    aname character varying(30),
    aaddr character varying(50),
    aphone character varying(10),
    aemail character varying(50),
    ausername character varying(30),
    apassword character varying(30),
    birthdate date,
    age character(3)
);


ALTER TABLE admin OWNER TO postgres;

--
-- Name: cbill; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cbill (
    bno integer NOT NULL,
    cusername character varying(20),
    login_time time without time zone,
    logout_time time without time zone,
    total_time time without time zone,
    total_bill double precision,
    membership_purchased character(3),
    mid integer,
    remaining_hrs double precision
);


ALTER TABLE cbill OWNER TO postgres;

--
-- Name: bill_bno_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE bill_bno_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE bill_bno_seq OWNER TO postgres;

--
-- Name: bill_bno_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE bill_bno_seq OWNED BY cbill.bno;


--
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE customer (
    cid integer NOT NULL,
    mid integer,
    cname character varying(50),
    caddr character varying(100),
    cemail character varying(30),
    cphone character varying(10),
    cusername character varying(20) NOT NULL,
    cpass character varying(64),
    birthdate date
);


ALTER TABLE customer OWNER TO postgres;

--
-- Name: customer_cid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE customer_cid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE customer_cid_seq OWNER TO postgres;

--
-- Name: customer_cid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE customer_cid_seq OWNED BY customer.cid;


--
-- Name: membership; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE membership (
    mid integer NOT NULL,
    mrate double precision,
    mhours double precision
);


ALTER TABLE membership OWNER TO postgres;

--
-- Name: pc; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pc (
    pcno integer NOT NULL,
    pcname character varying(10)
);


ALTER TABLE pc OWNER TO postgres;

--
-- Name: timerec; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE timerec (
    recno integer NOT NULL,
    login time without time zone,
    logout time without time zone
);


ALTER TABLE timerec OWNER TO postgres;

--
-- Name: bno; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cbill ALTER COLUMN bno SET DEFAULT nextval('bill_bno_seq'::regclass);


--
-- Name: cid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY customer ALTER COLUMN cid SET DEFAULT nextval('customer_cid_seq'::regclass);


--
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY admin (aid, aname, aaddr, aphone, aemail, ausername, apassword, birthdate, age) FROM stdin;
1	default	default	9999999999	default	admin	admin	1992-10-10	28 
2	default2	Homeless at the moment	1234567890	default2	admin1	admin1	2001-11-11	19 
\.


--
-- Name: bill_bno_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('bill_bno_seq', 1, true);


--
-- Data for Name: cbill; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cbill (bno, cusername, login_time, logout_time, total_time, total_bill, membership_purchased, mid, remaining_hrs) FROM stdin;
1	customer1	01:22:30	06:00:00	04:37:30	138.75	\N	\N	\N
\.


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY customer (cid, mid, cname, caddr, cemail, cphone, cusername, cpass, birthdate) FROM stdin;
7	0	customer1	221B Baker street\t	customer1@gmail.com	2323232323	customer1	customer1	2005-11-22
\.


--
-- Name: customer_cid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('customer_cid_seq', 7, true);


--
-- Data for Name: membership; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY membership (mid, mrate, mhours) FROM stdin;
1	200	10
2	400	20
3	1000	50
4	2000	100
0	\N	\N
\.


--
-- Data for Name: pc; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pc (pcno, pcname) FROM stdin;
1	PC-01
\.


--
-- Data for Name: timerec; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY timerec (recno, login, logout) FROM stdin;
1	04:20:00	03:22:00
\.


--
-- Name: admin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (aid);


--
-- Name: bill_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cbill
    ADD CONSTRAINT bill_pkey PRIMARY KEY (bno);


--
-- Name: customer_cid_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY customer
    ADD CONSTRAINT customer_cid_key UNIQUE (cid);


--
-- Name: customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (cusername);


--
-- Name: membership_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY membership
    ADD CONSTRAINT membership_pkey PRIMARY KEY (mid);


--
-- Name: pc_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pc
    ADD CONSTRAINT pc_pkey PRIMARY KEY (pcno);


--
-- Name: timerec_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY timerec
    ADD CONSTRAINT timerec_pkey PRIMARY KEY (recno);


--
-- Name: bill_cusername_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cbill
    ADD CONSTRAINT bill_cusername_fkey FOREIGN KEY (cusername) REFERENCES customer(cusername);


--
-- Name: cbill_mid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cbill
    ADD CONSTRAINT cbill_mid_fkey FOREIGN KEY (mid) REFERENCES membership(mid);


--
-- Name: customer_mid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY customer
    ADD CONSTRAINT customer_mid_fkey FOREIGN KEY (mid) REFERENCES membership(mid);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: admin; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE admin FROM PUBLIC;
REVOKE ALL ON TABLE admin FROM postgres;
GRANT ALL ON TABLE admin TO postgres;
GRANT ALL ON TABLE admin TO cmh;


--
-- Name: cbill; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE cbill FROM PUBLIC;
REVOKE ALL ON TABLE cbill FROM postgres;
GRANT ALL ON TABLE cbill TO postgres;
GRANT ALL ON TABLE cbill TO cmh;


--
-- Name: customer; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE customer FROM PUBLIC;
REVOKE ALL ON TABLE customer FROM postgres;
GRANT ALL ON TABLE customer TO postgres;
GRANT ALL ON TABLE customer TO cmh;


--
-- Name: customer_cid_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE customer_cid_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE customer_cid_seq FROM postgres;
GRANT ALL ON SEQUENCE customer_cid_seq TO postgres;
GRANT ALL ON SEQUENCE customer_cid_seq TO cmh;


--
-- Name: membership; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE membership FROM PUBLIC;
REVOKE ALL ON TABLE membership FROM postgres;
GRANT ALL ON TABLE membership TO postgres;
GRANT ALL ON TABLE membership TO cmh;


--
-- PostgreSQL database dump complete
--

