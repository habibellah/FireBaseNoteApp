package habibellah.ayata.firenote;

//this class to fill Note information of note
@SuppressWarnings("unused")
public class Note {
    private String title;
    private String note;
   private String id;
    public Note(String id,String title, String note) {
        this.title = title;
        this.note = note;
        this.id = id;
    }
    public Note()
    {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
