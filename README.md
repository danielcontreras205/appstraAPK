# 📱 Estructura del Proyecto - App Android (MVVM Escalable)

Este proyecto sigue una arquitectura basada en **MVVM** con un enfoque **modular y escalable**. Cada capa tiene responsabilidades claras y los paquetes están organizados por características y dominios.

---

## 📁 Estructura del Proyecto

```plaintext
com.tuapp
│
├── 📦 data                      # Acceso a datos: red, repositorio y sesión
│   ├── 🌐 remote                # Comunicación con servicios externos
│   │   ├── 🔌 api               # Interfaces de servicios (Retrofit)
│   │   │   └── UserService.kt
│   │   ├── 📄 dto               # Modelos para la comunicación con la API
│   │   │   ├── 📁 UsuarioDTO
│   │   │   │   ├── LoginRequest.kt
│   │   │   │   ├── LoginResponse.kt
│   │   │   │   └── PersonResponse.kt
│   │   └── ⚙️ retrofit          # Configuración del cliente Retrofit
│   │       └── 📁 login
│   │           └── RetrofitUsuario.kt
│   ├── 🗃️ repository            # Implementación de repositorios
│   │   └── UserRepository.kt
│   └── 🔐 session               # Gestión de sesión del usuario
│       └── SessionManager.kt
│
├── 🧠 domain                    # Lógica de negocio, mapeo y modelos puros
│   ├── 🔄 mapper                # Mapeo entre modelos
│   │   └── loginMapper.kt
│   ├── 🧍 model					 # Modelos independientes del backend
│   │   ├── 📁 user 
│   │   │    └── User.kt
│   ├── 📥 response              # Modelos de respuesta HTTP
│   │   ├── PersonaResponse
│   │   └── TokenResponse
│   └── 🧩 usecase               # Casos de uso (lógica de negocio)
│       └── UserCaseToken
│
├── 🖥️ presentation              # Capa de presentación (UI + ViewModels)
│   ├── 🏠 home	                 # Funcionalidad del home
│   │   ├── HomeFragment.kt
│   │   ├── HomeViewModel.kt
│   │   └── MenuActionHandler.kt # Interfas
│   ├── 🔐 login                 # Funcionalidad de inicio de sesión
│   │   ├── LoginFragment.kt
│   │   └── LoginViewModel.kt
│   ├── 👤 user                  # Funcionalidad de inicio de sesión
│   │   ├── UserFragment.kt
│   │   └── UserViewModel.kt
│   └── MainActivity.kt
│
├── 🧰 utils                     # Utilidades y constantes globales
│   └── constants
│       ├── BaseFragmentConMenu.kt
│       ├── GeneralPaths.kt
│       └── Usuario.kt

````


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

## Estructura Típica de Layout para Apps con DrawerLayout

En una aplicación Android con menú lateral (navigation drawer), es común tener una jerarquía de layouts como esta para manejar de forma organizada el DrawerLayout, el NavigationView, el Toolbar y los Fragments.

```plaintext
res/layout/
├── drawer_layout.xml        # Layout raíz que contiene el menú lateral (drawer)
│
├── activity_main.xml        # Layout principal que se incluye dentro de drawer_layout
│   ├── AppBarLayout
│   │   └── Toolbar          # Barra superior
│   └── FragmentContainerView (NavHost)  # Donde se cargan los fragments
│
├── nav_header.xml           # Cabecera personalizada del NavigationView
├── bar_menu_left.xml        # Archivo de menú para el NavigationView
````