package FileService;


/**
* FileService/StringSequenceHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from DocumentService.idl
* dimanche 27 octobre 2024 10 h 42 WET
*/

public final class StringSequenceHolder implements org.omg.CORBA.portable.Streamable
{
  public String value[] = null;

  public StringSequenceHolder ()
  {
  }

  public StringSequenceHolder (String[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = FileService.StringSequenceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    FileService.StringSequenceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return FileService.StringSequenceHelper.type ();
  }

}
