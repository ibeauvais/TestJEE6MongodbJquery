package fr.simplechat.common.xml;

import org.bson.types.ObjectId;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ObjectIdXmlAdapter extends XmlAdapter<String,ObjectId> {


    @Override
    public ObjectId unmarshal(String idString){
        if(idString==null)
            return null;
        return new ObjectId(idString);
    }

    @Override
    public String marshal(ObjectId id) {
        if(id==null)
            return null;
        return id.toStringMongod();
    }
}
