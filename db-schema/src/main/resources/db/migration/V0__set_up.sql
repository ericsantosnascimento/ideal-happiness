CREATE USER acme WITH password 'acme';

--CREATE SCHEMA acme;

--GRANT USAGE ON SCHEMA acme TO acme;
ALTER USER acme SET search_path = 'public';

-- ensure that user acme will have the needed privileges on new tables
ALTER DEFAULT PRIVILEGES
   IN SCHEMA PUBLIC
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLES
   TO acme;

-- ensure that user acme will have the needed privileges on new sequences
ALTER DEFAULT PRIVILEGES
   IN SCHEMA PUBLIC
GRANT USAGE ON SEQUENCES
   TO acme;

-- ensure that new functions will not have default privilege in public schema
ALTER DEFAULT PRIVILEGES
REVOKE EXECUTE ON FUNCTIONS
 FROM PUBLIC;

-- revoke default function privilege
REVOKE EXECUTE
   ON ALL FUNCTIONS IN SCHEMA PUBLIC
 FROM PUBLIC;