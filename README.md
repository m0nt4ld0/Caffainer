# ☕ Caffainer

**Docker container monitor made with Java 21, Spring Boot, and Docker Engine API**  
**Monitor de contenedores Docker hecho con Java 21, Spring Boot y Docker Engine API**

---

## 🚀 Features / Características

- 🔍 Real-time monitoring of running Docker containers  
  Monitoreo en tiempo real de contenedores Docker en ejecución
- 📊 Display of container status, stats, and logs  
  Visualización del estado, estadísticas y logs de los contenedores
- 🔐 User authentication using JWT tokens  
  Autenticación de usuarios mediante tokens JWT
- 📦 Integration with Docker Engine API  
  Integración con la API del motor de Docker

---

## 🛠️ Technologies / Tecnologías

- Java 21  
- Spring Boot  
- Docker Engine API  
- JWT (JSON Web Token)  
- RESTful API  

---

## 📦 Installation / Instalación

### Prerequisites / Requisitos previos

- Docker instalado y corriendo
- Java 21
- Maven

### Clone the repository / Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/caffainer.git
```
### Build & Run / Compilar y ejecutar

```bash
cd caffainer
mvn clean install
java -jar target/caffainer-*.jar
```

### 🔐 Authentication / Autenticación
Los endpoints protegidos requieren un token JWT.

Endpoint para login: POST /auth/login
```json
{
  "username": "your-username",
  "password": "your-password"
}
```
Respuesta:
```json
{
  "token": "jwt-token"
}
```
Incluir el token en los headers de las peticiones protegidas:
```bash
Authorization: Bearer jwt-token
```

### 📦 `GET /container/listRunningContainers`

**Descripción:**  
Obtiene una lista de todos los contenedores Docker que están actualmente en ejecución.
Respuesta:
```json
[
  "b3d4774f... - /jellyfin",
  "c4f5ceab2.. - /redis",
  "5209a3291... - /pma",
  "94cfd14f.... - /pigallery2",
  "2f4ed2f4.... - /plex",
  "fd890a52.... - /calibre"
]
```
---

👨‍💻 Author / Autor
Caffainer - Made with ☕ by Mariela Montaldo
Desarrollado para facilitar el monitoreo moderno de contenedores Docker.
