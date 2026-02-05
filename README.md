# CleanArchitectureStudy

> Clean Architecture 패턴을 학습하기 위한 Android 애니메이션 검색 앱

## 프로젝트 소개

이 프로젝트는 **Clean Architecture**와 **MVVM 패턴**을 실제로 적용해보기 위한 학습 프로젝트입니다.
Jikan API를 활용하여 애니메이션을 검색하고 북마크할 수 있는 기능을 제공합니다.

## 주요 기능

- 애니메이션 리스트 조회 (Jikan API)
- 실시간 검색 (300ms 디바운싱)
- 북마크 추가/삭제 (Room Database)
- 애니메이션 상세 정보 확인

## 기술 스택

### Language & Platform
- **Kotlin** 1.8.0
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 35 (Android 15)

### Architecture
- **Clean Architecture** (Domain, Data, Presentation 레이어 분리)
- **MVVM Pattern**
- **Multi-Module** (기능별 모듈 분리)

### UI
- **Jetpack Compose** (Material Design 3)
- **Navigation Component** (with Animation)
- **Coil** (이미지 로딩)

### Asynchronous
- **Kotlin Coroutines**
- **Flow** (반응형 데이터 스트림)

### Dependency Injection
- **Dagger Hilt**

### Network & Local
- **Retrofit** (REST API 통신)
- **Room Database** (로컬 데이터 저장)
- **OkHttp** (HTTP 클라이언트)

### Testing
- **JUnit**, **MockK**
- **Compose UI Test**

## 프로젝트 구조

### 멀티 모듈 아키텍처

```
CleanArchitectureStudy/
├── app/                    # 메인 애플리케이션 모듈
│   ├── MainActivity
│   ├── Navigation
│   └── Application
│
├── core/                   # 핵심 비즈니스 로직
│   ├── domain/            # Domain Layer
│   │   ├── dto/          # 도메인 모델
│   │   ├── repository/   # Repository 인터페이스
│   │   └── usecase/      # UseCase (비즈니스 로직)
│   │
│   └── data/             # Data Layer
│       ├── repository/   # Repository 구현체
│       ├── datasource/   # Remote/Local DataSource
│       ├── api/          # Retrofit API Service
│       ├── local/        # Room Database
│       ├── mapper/       # Entity ↔ DTO 변환
│       └── di/           # Hilt DI Modules
│
└── feature/              # Presentation Layer (UI)
    ├── home/            # 홈 화면
    ├── search/          # 검색 화면
    ├── favorite/        # 북마크 화면
    └── detail/          # 상세 화면
```

## Clean Architecture 3-Layer

### 1. Domain Layer (`core:domain`)
**역할**: 비즈니스 로직의 핵심, 외부 의존성 없음

- **DTO (Data Transfer Objects)**
  - `AnimeDto`: 애니메이션 데이터 모델
  - `FavoriteAnimeDto`: 북마크 데이터 모델

- **Repository Interface**
  - `AnimeRepository`: 데이터 접근 추상화

- **UseCase**
  - `GetAnimeListUseCase`: 애니메이션 관련 비즈니스 로직
    - 리스트 조회
    - 북마크 관리
    - 검색 필터링

### 2. Data Layer (`core:data`)
**역할**: 데이터 소스 관리 및 Repository 구현

- **Repository Implementation**
  - `AnimeRepositoryImpl`: Remote + Local 데이터 결합

- **Data Sources**
  - `AnimeRemoteDataSource`: Retrofit API 호출
  - `AnimeLocalDataSource`: Room Database 접근

- **API**
  - `AnimeService`: Jikan API v4 인터페이스

- **Database**
  - `AppDatabase`: Room Database
  - `FavoriteAnimeDao`: DAO
  - `FavoriteAnimeEntity`: Room Entity

- **Mapper**
  - Entity ↔ DTO 변환 함수

### 3. Presentation Layer (feature modules)
**역할**: UI 표시 및 사용자 인터랙션

- **MVVM 패턴**
  - ViewModel: 상태 관리 (`@HiltViewModel`)
  - Composable: UI 구성

- **Feature Modules**
  - `feature:home` - 애니메이션 리스트
  - `feature:search` - 검색 (디바운싱, Flow 연산자)
  - `feature:favorite` - 북마크 그리드 뷰
  - `feature:detail` - 상세 정보

## 주요 패턴 및 기술

### Dependency Injection (Hilt)
```kotlin
@HiltAndroidApp
class AnimeApplication: Application()

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase
) : ViewModel()
```

### Reactive Programming (Flow)
```kotlin
// ViewModel
private val _animeList = MutableStateFlow<List<AnimeDto>>(emptyList())
val animeList = _animeList.asStateFlow()

// UI
val animeList by viewModel.animeList.collectAsStateWithLifecycle()
```

### Repository Pattern
```kotlin
class AnimeRepositoryImpl(
    private val remoteDataSource: AnimeRemoteDataSource,
    private val localDataSource: AnimeLocalDataSource
) : AnimeRepository {
    override fun getAnimeList(): Flow<List<AnimeDto>> {
        return combine(
            remoteDataSource.getAnimeList(),
            localDataSource.getBookmarkList()
        ) { remote, local ->
            // Remote + Local 데이터 병합
        }
    }
}
```

### Advanced Flow Operators (Search)
```kotlin
searchQuery
    .debounce(300)              // 300ms 디바운싱
    .distinctUntilChanged()      // 중복 제거
    .flatMapLatest { query ->    // 최신 결과만
        useCase.searchAnimeList(query)
    }
    .onEach { _searchList.value = it }
    .launchIn(viewModelScope)
```

## 의존성 규칙 (Dependency Rule)

```
Presentation → Domain ← Data
```

- **Presentation Layer**는 Domain Layer만 의존
- **Data Layer**는 Domain Layer만 의존
- **Domain Layer**는 어떤 레이어에도 의존하지 않음 (순수 Kotlin)

이를 통해:
- 비즈니스 로직이 UI나 데이터 소스와 독립적
- 테스트 용이성 향상
- 유지보수성 및 확장성 증가

## API

- **Jikan API v4**: https://docs.api.jikan.moe/
- 별도의 API 키 불필요
- Endpoint: `GET https://api.jikan.moe/v4/anime`

## 빌드 및 실행

### 요구사항
- Android Studio Hedgehog 이상
- JDK 11 이상

### 빌드
```bash
git clone [repository-url]
cd CleanArchitectureStudy
./gradlew build
```

### 실행
Android Studio에서 프로젝트를 열고 Run을 클릭하거나:
```bash
./gradlew installDebug
```

## 학습 포인트

이 프로젝트를 통해 학습할 수 있는 내용:

1. **Clean Architecture 실전 적용**
   - 레이어 분리 및 의존성 규칙
   - Repository Pattern
   - UseCase Pattern

2. **멀티 모듈 프로젝트 구성**
   - 기능별 모듈 분리
   - 모듈 간 의존성 관리

3. **Jetpack Compose**
   - Declarative UI
   - State Management
   - Navigation

4. **Kotlin Coroutines & Flow**
   - 비동기 처리
   - 반응형 프로그래밍
   - Flow 연산자 활용

5. **Dagger Hilt**
   - 의존성 주입
   - 모듈 설정

6. **Local + Remote 데이터 결합**
   - Room Database
   - Retrofit API
   - 데이터 동기화

## 주요 학습 내용

### Clean Architecture 의존성 방향
```
┌─────────────────┐
│  Presentation   │ (feature modules)
└────────┬────────┘
         │ depends on
         ↓
┌─────────────────┐
│     Domain      │ (core:domain)
└────────┬────────┘
         ↑ depends on
         │
┌─────────────────┐
│      Data       │ (core:data)
└─────────────────┘
```

### 데이터 흐름
```
User Action
    ↓
Composable UI
    ↓
ViewModel
    ↓
UseCase
    ↓
Repository Interface (Domain)
    ↓
Repository Implementation (Data)
    ↓
DataSource (Remote/Local)
    ↓
API / Database
```

## 라이선스

이 프로젝트는 학습 목적으로 작성되었습니다.

## 참고 자료

- [Clean Architecture (Robert C. Martin)](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Android Architecture Guide](https://developer.android.com/topic/architecture)
- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Kotlin Flow Documentation](https://kotlinlang.org/docs/flow.html)
- [Jikan API Documentation](https://docs.api.jikan.moe/)
