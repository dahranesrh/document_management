package FileService;


/**
* FileService/FileManagerPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DocumentService.idl
* dimanche 27 octobre 2024 10 h 42 WET
*/

public abstract class FileManagerPOA extends org.omg.PortableServer.Servant
 implements FileService.FileManagerOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("createFile", new java.lang.Integer (0));
    _methods.put ("deleteFile", new java.lang.Integer (1));
    _methods.put ("listFiles", new java.lang.Integer (2));
    _methods.put ("readFile", new java.lang.Integer (3));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // FileService/FileManager/createFile
       {
         String fileName = in.read_string ();
         String content = in.read_string ();
         this.createFile (fileName, content);
         out = $rh.createReply();
         break;
       }

       case 1:  // FileService/FileManager/deleteFile
       {
         String fileName = in.read_string ();
         this.deleteFile (fileName);
         out = $rh.createReply();
         break;
       }

       case 2:  // FileService/FileManager/listFiles
       {
         String $result[] = null;
         $result = this.listFiles ();
         out = $rh.createReply();
         FileService.StringSequenceHelper.write (out, $result);
         break;
       }

       case 3:  // FileService/FileManager/readFile
       {
         String fileName = in.read_string ();
         String $result = null;
         $result = this.readFile (fileName);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:FileService/FileManager:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public FileManager _this() 
  {
    return FileManagerHelper.narrow(
    super._this_object());
  }

  public FileManager _this(org.omg.CORBA.ORB orb) 
  {
    return FileManagerHelper.narrow(
    super._this_object(orb));
  }


} // class FileManagerPOA
