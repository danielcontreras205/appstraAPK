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

## 🧭 Estructura del directorio `res/` en un proyecto Android

```plaintext
res/
├── layout/
│   ├── drawer_layout.xml           # Layout raíz que combina Toolbar, NavigationView y FragmentContainerView
│   │                               # Contiene el menú lateral y el contenido principal
│   │
│   ├── activity_main.xml           # Incluido en drawer_layout como contenido principal
│   │   ├── Toolbar                  # Barra superior de navegación
│   │   └── FragmentContainerView   # Contenedor para los Fragments gestionados por Navigation Component
│   │
│   ├── nav_header.xml              # Cabecera personalizada del NavigationView (imagen y textos)
│   ├── fragment_login.xml          # UI para LoginFragment
│   ├── fragment_home.xml           # UI para HomeFragment
│   ├── fragment_user.xml           # UI para UserFragment
│
├── navigation/
│   └── nav_graph.xml               # Grafo de navegación
│       ├── startDestination: loginFragment
│       ├── loginFragment → homeFragment (action)
│       ├── userFragment (no se accede directamente desde login, pero está definido)
│       └── acción global: action_global_loginFragment (reinicia el flujo al loginFragment)
│
├── menu/
│   ├── bar_menu_left.xml           # Ítems del menú lateral (Navigation Drawer)
│   │   ├── nav_person → userFragment (id esperable en controlador)
│   │   ├── nav_home → homeFragment
│   │   ├── nav_gallery
│   │   └── nav_slideshow
│   │
│   └── bar_menu_top.xml            # Opciones del menú superior (cerrar sesión, ajustes)
│
├── mipmap/
│   └── ic_launcher_round.png       # Icono usado en el encabezado del menú lateral
│
├── values/
│   ├── strings.xml                 # Contiene textos como menu_user, nav_header_title, etc.
│   └── dimens.xml                  # Dimensiones usadas en nav_header
│
└── drawable/
    └── color_menu.xml             # Fondo personalizado para el header del menú lateral

````