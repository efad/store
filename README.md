# HULK-STORE APP

La aplicación se encuentra distribuida de la siguiente manera:

## BACKEND

Desarrollada bajo la plataforma JEE

- hulkstore-modelo: Correspondiente a la capa de persistencia
- hulkstore-servicio: Correspondiente a la capa de negocio
- hulkstore-rest: Correspondiente a la interfaz de servicios rest

Probada sobre el servidor de aplicaciones WildFly 18 e Implementada en el IDE Eclipse.

## FRONTEND

Desarrollada bajo el framework ANGULAR

- hulkstore-administracion-web: Correspondiente a la administración del inventario.
- hulkstore-tienda-web: Correspondiente a la aplicación de la tienda destinada al público en general. 


# 1. Carga de tablas de Base de Datos sobre Postgresql

Importa scripts del archivo SQL llamado: /hulkstore-modelo/scripts/estructura-db.sql el cual contiene la estructura de tablas de la base de datos.

# 2. Configuración del DataSource de Postgresql sobre el standalone.xml de WildFly ejemplo:

```xml
<xa-datasource jndi-name="java:/hulkStoreDS" pool-name="hulkStoreDS" enabled="true" use-java-context="true">
                    <xa-datasource-property name="ServerName">
                        127.0.0.1
                    </xa-datasource-property>
                    <xa-datasource-property name="PortNumber">
                        5432
                    </xa-datasource-property>
                    <xa-datasource-property name="DatabaseName">
                        hulkstore
                    </xa-datasource-property>
                    <driver>postgresql</driver>
                    <security>
                        <user-name>postgres</user-name>
                        <password>admin</password>
                    </security>
                    <validation>
                        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker"/>
                        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter"/>
                    </validation>
                </xa-datasource>
                <drivers>
                    <driver name="h2" module="com.h2database.h2">
                        <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
                    </driver>
                    <driver name="postgresql" module="org.postgresql">
                        <xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class>
                    </driver>
                </drivers>
```

# 3. Importación del proyecto BackEnd mediante la importación de Maven.

En Eclipse puede importarse el proyecto completo el cual contiene el POM que cargará solamente los componentes necesarios.

# 4. Despliegue del proyecto FrontEnd

npm update

ng serve -o

