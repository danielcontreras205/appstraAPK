# 📱 Estructura del Proyecto - App Android (MVVM Escalable)

Este proyecto sigue una arquitectura basada en **MVVM** con un enfoque **modular y escalable**. Cada capa tiene responsabilidades claras y los paquetes están organizados por características y dominios.

---

## 📁 Estructura del Proyecto

com.tuapp
│
├── data # Acceso a datos: red, repositorio y sesión
│ ├── remote
│ │ ├── api # Interfaces de servicios (Retrofit)
│ │ │ └── TokenService.kt
│ │ ├── dto # Modelos para la comunicación con la API
│ │ │ ├── LoginRequest.kt
│ │ │ └── LoginResponse.kt
│ │ └── retrofit # Configuración del cliente Retrofit
│ │ └── RetrofitClient.kt
│ ├── repository # Implementación de repositorios
│ │ └── UserRepository.kt
│ └── session # Gestión de sesión del usuario
│ ├── SessionManager.kt
│ └── UserSessionModels.kt
│
├── domain # Lógica de negocio y modelos puros
│ ├── model # Modelos independientes del backend
│ │ └── User.kt
│ └── usecase # (Opcional) Casos de uso de la aplicación
│
├── presentation # Lógica de presentación (UI + ViewModels)
│ ├── login # Feature: login
│ │ ├── LoginFragment.kt
│ │ └── LoginViewModel.kt
│ └── MainActivity.kt
│
├── utils # Utilidades y constantes globales
│ └── constants
│ ├── GeneralPaths.kt
│ └── Token.kt


---

## 📦 Descripción de Paquetes

| Carpeta | Descripción |
|--------|-------------|
| `data/remote/api` | Interfaces Retrofit que definen los endpoints |
| `data/remote/dto` | Clases que representan la solicitud/respuesta del backend |
| `data/remote/retrofit` | Configuración del cliente HTTP (Retrofit) |
| `data/repository` | Encapsula la lógica de acceso a datos, usado por ViewModel |
| `data/session` | Clases relacionadas con el manejo de sesión (SharedPreferences) |
| `domain/model` | Modelos puros de negocio, sin dependencia de red o UI |
| `presentation/login` | Fragmento y ViewModel de la pantalla de login |
| `utils/constants` | Constantes globales como rutas o URLs base |
