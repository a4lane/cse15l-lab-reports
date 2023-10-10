import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
   
    String[] list = new String[100];
    int index = 0;

    public String displayList(String[] l){
        String output = "";
        for (String s : l){
            if (s != null)
                output += s + "\n";
        }
        return output;
    }

    public String[] searchList(String target){
        String[] matches = new String[list.length];
        int index = 0;
        for (String s : list){
            if (s != null){
                if (s.contains(target)){
                    matches[index] = s;
                    index++;
                }

            }
        }
        return matches;
    }


    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format(displayList(list));
        } 
        else if (url.getPath().contains("/search")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                    String target = parameters[1];
                    String matches[] = searchList(target);
                    if (matches[0] == null)
                        return "No matches found for \'" + target + "\'.";
                    else
                        return String.format("Matches found for " + target + ":\n" + displayList(matches));
                }

        }
        else {
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    list[index] = parameters[1];
                    index++;
                    return String.format(parameters[1] + " has been added!\n");
                }
            }
            
        }
        return "404 Not Found!";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
