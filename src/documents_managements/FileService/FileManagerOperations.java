package FileService;


/**
* FileService/FileManagerOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DocumentService.idl
* dimanche 27 octobre 2024 10 h 42 WET
*/

public interface FileManagerOperations 
{
  void createFile (String fileName, String content);
  void deleteFile (String fileName);
  String[] listFiles ();
  String readFile (String fileName);
} // interface FileManagerOperations
