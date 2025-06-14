# ☕ Caffainer

**Docker container monitor made with Java 21, Spring Boot, and Docker Engine API**  
**Monitor de contenedores Docker hecho con Java 21, Spring Boot y Docker Engine API**

---

## 🚀 Features / Características

- 🔍 Real-time monitoring of running Docker containers  
  Monitoreo en tiempo real de contenedores Docker en ejecución
- 📊 Display of container status, stats, and logs  
  Visualización del estado, estadísticas y logs de los contenedores
- Tests unitarios con JUnit y Mockito
- HTTPS
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
## 🔐 Configuración HTTPS con certificado autofirmado

Este proyecto utiliza HTTPS en el entorno de desarrollo. Para ello, es necesario generar un certificado autofirmado con una clave privada y configurarlo en el archivo `application-dev.properties`.

### 🛠️ Generación del certificado

Ejecutá el siguiente comando para generar tu certificado usando `keytool`:

```bash
keytool -genkeypair \
  -alias caffainer \
  -keyalg RSA \
  -keysize 2048 \
  -storetype PKCS12 \
  -keystore keystore.p12 \
  -validity 3650

Este comando generará un archivo keystore.p12 con una validez de 10 años.
📁 Ubicación del certificado

## Colocá el archivo generado keystore.p12 en la siguiente ruta del proyecto:
```bash
/src/main/resources/keystore.p12
```

##  ⚙️ Configuración en application-dev.properties
Agregá la siguiente configuración para habilitar HTTPS en tu entorno de desarrollo:

```bash
# Certificado de seguridad 
server.port=8443
server.ssl.key-store-type=PKCS12
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=YOUR_PASSWORD
server.ssl.key-alias=caffainer
```
🧠 Reemplazá YOUR_PASSWORD con la contraseña que ingresaste al generar el certificado.

✅ Una vez configurado, tu aplicación estará disponible de forma segura en:
```https://localhost:8443```

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

### 👨‍💻 Author / Autor
Caffainer - Made with ☕ by Mariela Montaldo
Desarrollado para facilitar el monitoreo moderno de contenedores Docker.
