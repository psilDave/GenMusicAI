# GenMusicAI - Text-Based Music Generation

## About the Project
This repository contains the source code and documentation for the proof of concept (PoC) of **GenMusicAI**, an application developed to generate music from textual descriptions using artificial intelligence. 
The project is part of the **Final Year Project (TCC)** by **Davi Pereira da Silva**, presented at the **University of Campinas (UNICAMP)** in 2024.

## Objective
The main objective of GenMusicAI allows users to create music from textual prompts directly on Android devices.

## Technologies Used
- **Language:** Kotlin
- **Framework:** Jetpack Compose (for UI)
- **Architecture:** MVVM (Model-View-ViewModel)
- **Database:** Room Database
- **Dependency Injection:** Hilt
- **API Calls:** Retrofit
- **AI Model:** Suno AI (based on the Bark model)
- **Platform:** Native Android (compatible with Android 7.0+)

## Application Structure
The application consists of five main screens:
1. **Splash Screen** - Displays the logo when launching the app.
2. **Main Screen** - Lists previous conversations and provides an option to start a new chat.
3. **Chat Screen (Music Chat Screen)** - Where the user inputs textual descriptions and receives the generated music.
4. **Settings Screen** - Allows modifying application settings.
5. **AI Model Selection Screen** - Allows users to choose the AI model for music generation.

## Installation and Execution
### Requirements:
- Android Studio Flamingo or later
- Android device running version 7.0+
- Internet connection (for API calls)

### Steps:
1. Clone the repository:
   ```sh
   git clone https://github.com/psilDave/TCC.git
   ```
2. Open the project in Android Studio.
3. Set up an emulator or connect a physical device.
4. Compile and run the application.

## Demonstration
Watch an example of the app in action in the following video:

[![GenMusicAI - Demo](https://img.youtube.com/vi/kx2HO6pW0lc/0.jpg)](https://youtu.be/kx2HO6pW0lc)

## Folder Structure
```
TCC-GenMusicAI/
│── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/genmusicai/
│   │   │   │   ├── data/    # Model Layer
│   │   │   │   ├── ui/      # View and ViewModel Layer
│   │   │   │   ├── di/      # Dependency Injection (Hilt)
│   ├── build.gradle
│── README.md
```

## Results
The main outcomes of the project include:
- Implementation of a functional AI-based music generation system.
- Integration with the Suno AI API for text-to-music conversion.
- Development of an intuitive chat-based interface.

## Contribution
This repository is open to contributions! If you have suggestions or improvements, feel free to submit a Pull Request.

## License
This project is distributed under the MIT License. See the `LICENSE` file for more details.

---
**Author:** Davi Pereira da Silva  
**Advisor:** Romis Ribeiro de Faissol Attux  
**University:** UNICAMP  

