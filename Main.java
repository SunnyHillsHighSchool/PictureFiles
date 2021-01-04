class Main 
{
  /* Main method for testing */
  public static void main(String[] args) 
  {
    Picture pic = new Picture("caterpillar.jpg");
     
    pic.write("output.jpg");
  }
}

/*
  This is Picture Lab with all of the GUI stripped out.
  I also simplified some things, removed some unused 
  methods, and removed the inheritance aspect (as I used 
  it in Unit 8 before I covered inheritance).

  The pic.write("output.jpg") line creates output in the
  output.jpg file at left.  Repl.it lets you open it
  and view it directly so you can see what effect your
  code had on the image.
*/