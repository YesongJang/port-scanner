
import java.net.Socket;
import java.util.Scanner;
import java.net.InetSocketAddress;
import java.util.InputMismatchException;

public class PortScanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);

        try{
            // We ask the user for a host because a port is always checked on a specific machine/server.
            // Example hosts:
            // - google.com
            // - scanme.nmap.org
            // - 127.0.0.1
            System.out.print("Enter host: ");
            String host = scanner.nextLine();

            // We ask for a start port and end port because a port scanner usually checks a range of ports,
            // not just one single port.
            System.out.print("Enter start port: ");
            int startPort = scanner.nextInt();

            System.out.print("Enter end port: ");
            int endPort = scanner.nextInt();

            if (!isValidInput(host, startPort, endPort)) {
                scanner.close();
                return;
            }

            System.out.println("\nScanning host: " + host);
            System.out.println("Port range: " + startPort + " - " + endPort);

            scanPorts(host, startPort, endPort);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Please enter numbers for port values.");
        } finally {
            scanner.close();
        }
    }

/*
Socket is a class in the java.net package
that allows a program to create a network connection to another machine.
In this project, a socket is used to attempt a TCP connection to a specific
host and port. If the connection is successful, the port is considered open.
If the connection fails, the port is considered closed or unreachable.
Because the Socket class belongs to the java.net package, it must be imported
before it can be used in the program.
*/
    public static boolean isPortOpen(String host, int port) {
        // A Socket is a networking object used to create a TCP connection
        // between this program and another machine on a specific port.
        //
        // In simple terms:
        // - host = which computer/server we want to reach
        // - port = which "door" or service on that computer we want to test
        //
        // If the connection succeeds, the port is open.
        // If it fails, the port is closed or unreachable.

        // try-with-resources automatically closes the socket after use.
        // This is important because sockets use system/network resources.
        try (Socket socket = new Socket()) {

            // InetSocketAddress combines a host and a port into one address object.
            // It tells Java exactly where we want to connect.

            // Example:
            // new InetSocketAddress("scanme.nmap.org", 80)
            // means "connect to scanme.nmap.org on port 80"
            //
            // The second argument in connect(..., 500) is the timeout in milliseconds.
            // 500 ms = 0.5 seconds
            //
            // This is important because without a timeout,
            // the program may wait too long on some ports and appear stuck.
            socket.connect(new InetSocketAddress(host, port), 500);

            // If connect() succeeds, that means the port accepted the TCP connection,
            // so we return true.
            return true;

        } catch (Exception e) {

            // We use try-catch because network connections can fail for many normal reasons:
            // - the port is closed
            // - the host is unreachable
            // - the connection times out
            // - DNS lookup fails
            //
            // In port scanning, failure is expected very often.
            // So instead of crashing the whole program.
            // we catch the exception and simply return false.
            return false;
        }
    }


    public static boolean isValidInput(String host, int startPort, int endPort){
        if (startPort < 0 || endPort > 65535 || startPort > endPort) {
            System.out.println("Invalid port range. Ports must be between 0 and 65535, and startPort must be less than or equal to endPort.");
            return false;
        }

        if (host.isBlank()){
            System.out.println("Host cannot be empty.");
            return false;
        }
        return true;
    }

    public static void scanPorts(String host, int startPort, int endPort){
        int openCount = 0; // Count the open ports

        // This loop checks every port from startPort to endPort.
        // If a connection succeeds, we consider that port OPEN.
        for (int port = startPort; port <= endPort; port++) {
            if (isPortOpen(host, port)) {
                System.out.println("Port " + port + " is OPEN.");
                openCount ++;
            }
        }

        System.out.println("Scan complete.");

        if (openCount == 0) {
            System.out.println("No open ports found in the given range.");
        } else {
            System.out.println("Total open ports found: " + openCount);
        }

    }
}