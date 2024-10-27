
import javax.swing.*;
import FileService.FileManager;
import documents_managements.FileService.FileManagerHelper;
import org.omg.CORBA.ORB;

public class FileManagerClientUI extends JFrame {
    private FileManager fileManager;

    public FileManagerClientUI(String[] args) {
        // Initialize CORBA
        try {
            ORB orb = ORB.init(args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            FileManager manager = FileManagerHelper.narrow(objRef);
            this.fileManager = manager;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set up the UI
        setTitle("CORBA File Manager");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create UI components
        JTextField fileNameField = new JTextField(20);
        JTextArea fileContentArea = new JTextArea(10, 20);
        JButton createButton = new JButton("Create File");
        JButton deleteButton = new JButton("Delete File");
        JButton readButton = new JButton("Read File");
        JButton listButton = new JButton("List Files");

        // Add action listeners
        createButton.addActionListener(e -> {
            try {
                fileManager.createFile(fileNameField.getText(), fileContentArea.getText());
                JOptionPane.showMessageDialog(this, "File created!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                fileManager.deleteFile(fileNameField.getText());
                JOptionPane.showMessageDialog(this, "File deleted!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        readButton.addActionListener(e -> {
            try {
                String content = fileManager.readFile(fileNameField.getText());
                fileContentArea.setText(content);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        listButton.addActionListener(e -> {
            try {
                String[] files = fileManager.listFiles();
                fileContentArea.setText(String.join("\n", files));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Arrange components in a layout
        JPanel panel = new JPanel();
        panel.add(new JLabel("File Name:"));
        panel.add(fileNameField);
        panel.add(createButton);
        panel.add(deleteButton);
        panel.add(readButton);
        panel.add(listButton);
        panel.add(new JScrollPane(fileContentArea));

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FileManagerClientUI(args);
    }
}
