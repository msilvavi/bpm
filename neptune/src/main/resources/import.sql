--
-- JBoss, Home of Professional Open Source
-- Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements
-- Password encoding SHA-256 with base64. Admin password is Admin1_Admin1
INSERT INTO users(user_id, name, surname, lastname, fullname, password, username, active) VALUES (nextval('users_sequence'), 'Isaac', 'Galvan', 'Lañado', 'Isaac Galvan Lañado' ,'08r3o+6YdBD4upKA1Ym7TNlIwyT7px/lt9BAx+z2YFA=', 'igalvan',true);
INSERT INTO roles(role_id,role_name,role_description) VALUES (nextval('roles_sequence'),'admin','BPMS Admin');
INSERT INTO user_roles VALUES (currval('users_sequence'),currval('roles_sequence'));
INSERT INTO roles(role_id,role_name,role_description) VALUES (nextval('roles_sequence'),'rest-all','BPMS rest connection');
INSERT INTO user_roles VALUES (currval('users_sequence'),currval('roles_sequence'));
INSERT INTO roles(role_id,role_name,role_description) VALUES (nextval('roles_sequence'),'consultant','Consultant Role');
INSERT INTO user_roles VALUES (currval('users_sequence'),currval('roles_sequence'));
INSERT INTO roles(role_id,role_name,role_description) VALUES (nextval('roles_sequence'),'architect','Architect Role');
INSERT INTO user_roles VALUES (currval('users_sequence'),currval('roles_sequence'));
INSERT INTO roles(role_id,role_name,role_description) VALUES (nextval('roles_sequence'),'accountmgr','Account Manager Role');
INSERT INTO user_roles VALUES (currval('users_sequence'),currval('roles_sequence'));

-- Setup product list
INSERT INTO products(product_id,code,name) VALUES (nextval('products_sequence'),'RHEL','Red Hat Enterprise Linux');
INSERT INTO products(product_id,code,name) VALUES (nextval('products_sequence'),'RHEV','Red Hat Enterprise Virtualization');
INSERT INTO products(product_id,code,name) VALUES (nextval('products_sequence'),'STG','Red Hat Enterprise Linux');
INSERT INTO products(product_id,code,name) VALUES (nextval('products_sequence'),'EAP','JBoss EAP');


