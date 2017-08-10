public class FileTable {

   private Vector table;         // the actual entity of this file table
   private Directory dir;        // the root directory 

   public FileTable( Directory directory ) { // constructor
      table = new Vector( );     // instantiate a file (structure) table
      dir = directory;           // receive a reference to the Director
   }                             // from the file system

   // major public methods
   public synchronized FileTableEntry falloc( String filename, String mode ) {
   		// allocate a new file (structure) table entry for this file name
   		FileTableEntry newEntry = new FileTableEntry(newInode, iNumber, mode);
	    // allocate/retrieve and register the corresponding inode using dir
	    // check if Inode exists if doesn't exist, create a new one
	    iNumber = dir.namei(String fileName);
	    if(iNumber == -1) {
	   		iNumber = dir.ialloc(fileName);
	    }
	    Inode newInode = new Inode(iNumber);
	    //add file table to list of tables
	    this.table.add(newEntry);
	    // increment this inode's count
	    newInode.count++;
	    // immediately write back this inode to the disk
	    newInode.toDisk(iNumber);
	    // return a reference to this file (structure) table entry
	    return newEntry;
   }

   public synchronized boolean ffree( FileTableEntry e ) {
      // receive a file table entry reference
      // save the corresponding inode to the disk
      // free this file table entry.
      // return true if this file table entry found in my table
   }

   public synchronized boolean fempty( ) {
      return table.isEmpty( );  // return if table is empty 
   }                            // should be called before starting a format
}