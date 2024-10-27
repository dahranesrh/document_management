import FileService.FileManagerPOA;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NameComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManagerImpl extends FileManagerPOA {
    private ORB orb;
    private final Map<String, String> fileStorage = new HashMap<>(); // Simulates file storage

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }

    // Implement the methods from the IDL
    @Override
    public void createFile(String fileName, String content) {
        fileStorage.put(fileName, content);
        System.out.println("File created: " + fileName);
    }

    @Override
    public void deleteFile(String fileName) {
        fileStorage.remove(fileName);
        System.out.println("File deleted: " + fileName);
    }

    @Override
    public String[] listFiles() {
        List<String> files = new ArrayList<>(fileStorage.keySet());
        return files.toArray(new String[0]);
    }

    @Override
    public String readFile(String fileName) {
        return fileStorage.getOrDefault(fileName, "File not found.");
    }

    public static void main(String[] args) {
        try {
            // Initialize ORB
            ORB orb = ORB.init(args, null);

            // Get reference to RootPOA and activate the POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // Create an instance of FileManagerImpl and register it with the ORB
            FileManagerImpl fileManagerImpl = new FileManagerImpl();
            fileManagerImpl.setORB(orb);

            // Obtain a CORBA reference for the servant
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(fileManagerImpl);
            FileService.FileManager href = FileService.FileManagerHelper.narrow(ref);

            // Bind the object reference in the naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            NameComponent path[] = ncRef.to_name("FileManager");
            ncRef.rebind(path, href);

            System.out.println("FileManager CORBA server is running...");

            // Keep the server running
            orb.run();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("FileManager server exiting...");
    }
}
