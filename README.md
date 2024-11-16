# RestauranteIsrael
El proyecto se encargar de procesar ordenes de comida para lo cual se pondran a disposición 
el siguiente flujo:

1. **Consulta el cliente**
   - exite: se obtiene la info
   - no existe: se ingresa el cliente 
2. **Obtener menu**
3. **Seleccionar platillo**
4. **Ingresar orden**
   - opcion: Cancelar    
   - opcion: En trancito

5. **Entregar orden**

---
# Diseño

En este proyecto se aplica una arquitecturas en micriservcicios y api-gateway.
se generan 3 proyectos rest api que se encargaran de administrar un solo componente,
mientras que un api gateway se encargara de centralizar todas las peticiones y 
distribuirlas al proyecto correspondiente.

## Componentes

| **Componente**        | **Description**                                       |
|:----------------------|:------------------------------------------------------|
| **api-person**        | Se encarga de los clientes                            |
| **api-products**      | Se los platillos de comida                            |
| **api-orders**        | Se encarga de las ordenes                             |
| **uisrael-lib-utils** | Contiene las clases estandar para todos los proyectos |
| **api-gateway**       | Se encarga de centralizar y distribuir las peticiones |






