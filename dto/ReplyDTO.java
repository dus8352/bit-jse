package dto;

import java.util.Calendar;

public class ReplyDTO {
    private int id;
    private int boardId;
    private int writerId;
    private String content;
    private Calendar writtenDate;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getBoardId() {
        return boardId;
    }
    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
    public int getWriterId() {
        return writerId;
    }
    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Calendar getWrittenDate() {
        return writtenDate;
    }
    public void setWrittenDate(Calendar writtenDate) {
        this.writtenDate = writtenDate;
    }
    
    public boolean equals(Object o) {
        if(o instanceof ReplyDTO) {
            ReplyDTO r = (ReplyDTO)o;
            if(id == r.id) {
                return true;
                
            }
            
            
        }
        
        return false;
    }
}
