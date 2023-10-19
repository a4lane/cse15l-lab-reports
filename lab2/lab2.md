**Lab Report 2**

#Part 1
import java.io.IOException;
import java.net.URI;



      class Handler implements URLHandler {

          String[] list = new String[100];
          int index = 0;

          public String displayList(String[] l){
              String output = "";
              int count = 1;
              for (String s : l){
                  if (s != null)
                      output += count + ": "+ s + "\n";
                      count++;
              }
              return output;
          }



          public String handleRequest(URI url) {
              if (url.getPath().equals("/")) {
                  return String.format(displayList(list));
              } 
              if (url.getPath().contains("/add-message")) {
                  String[] parameters = url.getQuery().split("=");
                  if (parameters[0].equals("s")) {
                       list[index] = parameters[1];
                       index++;
                       return String.format(displayList(list));
                }
              }
              return "404 Not Found!";
          }
      }

      class StringServer {
          public static void main(String[] args) throws IOException {
              if(args.length == 0){
                  System.out.println("Missing port number! Try any number between 1024 to 49151");
                  return;
              }

              int port = Integer.parseInt(args[0]);

              Server.start(port, new Handler());
          }
      }

