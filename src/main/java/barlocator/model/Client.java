package main.java.barlocator.model;

import com.google.gson.Gson;
import main.java.com.barlocator.server.Body;
import main.java.com.barlocator.server.Header;
import main.java.com.barlocator.server.Request;
import main.java.com.barlocator.server.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket server;
    private DataInputStream in;
    private DataOutputStream out;
    private final int _PORT = 3000;
    private final String _HOST = "localhost";
    private Request req;
    private Response res;
    private Gson gson;

    public Client() {
        this.gson = new Gson();
    }

    public void sendRequest(String method, Body body){
        try {
            this.req = new Request(new Header(method),body);
            server = new Socket(_HOST,_PORT);
            in = new DataInputStream(server.getInputStream());
            out = new DataOutputStream(server.getOutputStream());
            out.writeUTF(gson.toJson(req));
            res = gson.fromJson(in.readUTF(),Response.class);
            server.close();
        } catch (IOException e) {
            System.out.println("Cannot connect to server");
            e.printStackTrace();
        }
    }

    public Socket getServer() {
        return server;
    }

    public void setServer(Socket server) {
        this.server = server;
    }

    public DataInputStream getIn() {
        return in;
    }

    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    public Request getReq() {
        return req;
    }

    public void setReq(Request req) {
        this.req = req;
    }

    public Response getRes() {
        return res;
    }

    public void setRes(Response res) {
        this.res = res;
    }
}
