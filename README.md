# Port Scanner

A simple Java-based port scanner that checks whether ports are open on a given host.

## Features

- Takes a host, start port, and end port as user input
- Scans all ports in the given range
- Detects open ports using TCP socket connections
- Uses a timeout to avoid hanging on unresponsive ports
- Validates port range input
- Handles invalid input types for port values
- Displays the total number of open ports found

## Technologies Used

- Java
- IntelliJ IDEA
- TCP Socket Programming
- Input Validation
- Exception Handling

## How It Works

The program asks the user to enter:

- a host name or IP address
- a start port
- an end port

It then attempts to connect to each port in the given range using a TCP socket.

If the connection succeeds, the port is considered **open**.  
If the connection fails or times out, the port is considered **closed or unreachable**.

## Example Output

Enter host: scanme.nmap.org  
Enter start port: 75  
Enter end port: 85  

Scanning host: scanme.nmap.org  
Port range: 75 - 85  
Port 80 is OPEN.  
Scan complete.  
Total open ports found: 1  

## What I Learned

Through this project, I practiced:

- Java socket programming
- TCP connection testing
- exception handling with try-catch
- input validation
- organizing code into reusable methods

## Future Improvements

- store open ports in a list and display them at the end
- support multi-threaded scanning for better performance
- add service name lookup for common ports
- build a GUI version
