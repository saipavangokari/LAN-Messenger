# LAN Messenger

LAN Messenger is a real-time communication application designed for local networks. Built using **Java Socket Programming**, the application facilitates secure messaging between multiple users connected over the same network. The application employs a **Server-Client architecture**, allowing seamless multi-client communication. 

## Key Features

- **Real-Time Communication**:
  - Enables instant messaging over a local network.

- **Server-Client Architecture**:
  - A central server facilitates connections for multiple clients, ensuring secure communication.

- **Multi-Client Support**:
  - Multiple users can connect to the server and communicate simultaneously.

- **User-Friendly Interface**:
  - Developed with **Java Swing** for an intuitive and responsive UI.

- **Secure Connection**:
  - Establishes communication using static IP and port, ensuring reliability.

## How It Works

1. **Run the Server Application**:
   - Start the server application, which generates a **static IP address** and a **static port number**.
   - These details are required for client-side connections.

2. **Run the Client Application**:
   - Start the client application, where users are prompted to:
     - Enter the **IP address** and **port number** provided by the server.
     - Provide a username for registration.
   - After connection, the user can start messaging.

3. **Multi-Client Support**:
   - Any number of clients can connect to the server using the same IP address and port number for group messaging.

## Tech Stack

- **Programming Language**: Java
- **Networking**: Java Socket Programming
- **User Interface**: Java Swing

## Installation

### Prerequisites

Ensure you have the following installed:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)

### Setup Steps

1. **Clone the repository**:

   ```bash
   git clone https://github.com/your-username/lan-messenger.git
   cd lan-messenger
   ```

2. **Run the Server Application**:
   - Navigate to the server directory and compile the server code:
   
     ```bash
     cd Server
     javac Server.java
     java Server
     ```

   - Note the **IP address** and **port number** displayed after starting the server.

3. **Run the Client Application**:
   - Navigate to the client directory and compile the client code:
   
     ```bash
     cd Client
     javac ServerIP.java
     java ServerIP
     ```

   - Enter the **IP address**, **port number**, and **username** to connect to the server.

4. **Start Messaging**:
   - Once connected, you can send messages to other clients on the network.

## Screenshots

**Server**:

![Server](https://github.com/user-attachments/assets/a2638bf3-43f9-49f3-a5ff-4137d2269453)

**Client-Connection**:

![Client-Connection](https://github.com/user-attachments/assets/1aaf9417-7a06-4fe4-ba80-61b089d9a0ba)

**Client-Chatbox**:

![Client-Chatbox](https://github.com/user-attachments/assets/e650792d-0bfc-4f6a-bacc-1d7592fc69b3)





