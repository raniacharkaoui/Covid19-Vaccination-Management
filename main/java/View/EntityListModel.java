/*
 * This is complete copy of the code given in the labs
 * 
 */
package View;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @authors Rania Charkaoui, Arthur Elskens & Gilles Feron
 */
public class EntityListModel<T> extends AbstractListModel {
    
    private List<T> entities;
    
    public EntityListModel(List<T> entities){
        if( entities == null ){
            entities = new ArrayList();
        }
        this.entities = entities;
    }
    
    public void setList(List<T> entities){
        this.entities = entities;
    }
    
    public List<T> getList(){
        return entities;
    }
    
    @Override
    public int getSize() {
        return entities.size();
    }

    @Override
    public Object getElementAt(int index) {
        return entities.get(index);
    }
    
}
