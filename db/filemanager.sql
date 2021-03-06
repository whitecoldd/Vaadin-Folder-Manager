PGDMP     /              
        z           file_manager    14.3    14.3                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            	           1262    16431    file_manager    DATABASE     i   CREATE DATABASE file_manager WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';
    DROP DATABASE file_manager;
                postgres    false            @           1247    16503    color    TYPE     R   CREATE TYPE public.color AS ENUM (
    '0',
    '1',
    '2',
    '3',
    '4'
);
    DROP TYPE public.color;
       public          postgres    false            C           1247    16558    colors    TYPE     d   CREATE TYPE public.colors AS ENUM (
    'BLACK',
    'WHITE',
    'RED',
    'BLUE',
    'GREEN'
);
    DROP TYPE public.colors;
       public          postgres    false            ?            1259    16442    document    TABLE     ?   CREATE TABLE public.document (
    id bigint NOT NULL,
    name character varying(100) NOT NULL,
    description text,
    url character varying(1000) NOT NULL,
    doc_type character varying(255) NOT NULL,
    folder_id bigint NOT NULL
);
    DROP TABLE public.document;
       public         heap    postgres    false            ?            1259    16441    document_id_seq    SEQUENCE     x   CREATE SEQUENCE public.document_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.document_id_seq;
       public          postgres    false    212            
           0    0    document_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.document_id_seq OWNED BY public.document.id;
          public          postgres    false    211            ?            1259    16433    folder    TABLE     ?   CREATE TABLE public.folder (
    id bigint NOT NULL,
    name character varying(100) NOT NULL,
    description text,
    parent_id bigint,
    color character varying
);
    DROP TABLE public.folder;
       public         heap    postgres    false            ?            1259    16432    folder_id_seq    SEQUENCE     v   CREATE SEQUENCE public.folder_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.folder_id_seq;
       public          postgres    false    210                       0    0    folder_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.folder_id_seq OWNED BY public.folder.id;
          public          postgres    false    209            ?            1259    16450    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false            i           2604    16445    document id    DEFAULT     j   ALTER TABLE ONLY public.document ALTER COLUMN id SET DEFAULT nextval('public.document_id_seq'::regclass);
 :   ALTER TABLE public.document ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    211    212            h           2604    16436 	   folder id    DEFAULT     f   ALTER TABLE ONLY public.folder ALTER COLUMN id SET DEFAULT nextval('public.folder_id_seq'::regclass);
 8   ALTER TABLE public.folder ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    209    210                      0    16442    document 
   TABLE DATA           S   COPY public.document (id, name, description, url, doc_type, folder_id) FROM stdin;
    public          postgres    false    212   ,                  0    16433    folder 
   TABLE DATA           I   COPY public.folder (id, name, description, parent_id, color) FROM stdin;
    public          postgres    false    210   I                  0    0    document_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.document_id_seq', 13, true);
          public          postgres    false    211                       0    0    folder_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.folder_id_seq', 34, true);
          public          postgres    false    209                       0    0    hibernate_sequence    SEQUENCE SET     B   SELECT pg_catalog.setval('public.hibernate_sequence', 442, true);
          public          postgres    false    213            o           2606    16449    document document_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.document
    ADD CONSTRAINT document_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.document DROP CONSTRAINT document_pkey;
       public            postgres    false    212            m           2606    16440    folder folder_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.folder
    ADD CONSTRAINT folder_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.folder DROP CONSTRAINT folder_pkey;
       public            postgres    false    210            p           1259    16545    fki_fkey_folder    INDEX     I   CREATE INDEX fki_fkey_folder ON public.document USING btree (folder_id);
 #   DROP INDEX public.fki_fkey_folder;
       public            postgres    false    212            q           1259    16574    fki_fkey_folderinho    INDEX     M   CREATE INDEX fki_fkey_folderinho ON public.document USING btree (folder_id);
 '   DROP INDEX public.fki_fkey_folderinho;
       public            postgres    false    212            j           1259    16478    fki_folder_fkey    INDEX     G   CREATE INDEX fki_folder_fkey ON public.folder USING btree (parent_id);
 #   DROP INDEX public.fki_folder_fkey;
       public            postgres    false    210            k           1259    16551    fki_itself_fkey    INDEX     G   CREATE INDEX fki_itself_fkey ON public.folder USING btree (parent_id);
 #   DROP INDEX public.fki_itself_fkey;
       public            postgres    false    210            s           2606    16569    document fkey_folderinho    FK CONSTRAINT     ?   ALTER TABLE ONLY public.document
    ADD CONSTRAINT fkey_folderinho FOREIGN KEY (folder_id) REFERENCES public.folder(id) MATCH FULL ON DELETE CASCADE;
 B   ALTER TABLE ONLY public.document DROP CONSTRAINT fkey_folderinho;
       public          postgres    false    212    3181    210            r           2606    16546    folder itself_fkey    FK CONSTRAINT     ?   ALTER TABLE ONLY public.folder
    ADD CONSTRAINT itself_fkey FOREIGN KEY (parent_id) REFERENCES public.folder(id) MATCH FULL ON DELETE CASCADE;
 <   ALTER TABLE ONLY public.folder DROP CONSTRAINT itself_fkey;
       public          postgres    false    210    210    3181                  x?????? ? ?          8   x?36?L?,*.QH??II-?,??,V?KMM)V(?WHJU ?r??q??p??qqq ???     