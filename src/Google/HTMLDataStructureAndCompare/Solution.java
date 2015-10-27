import java.util.*;

/**
 * Created by eric on 2015-10-26.
 */
public class Document {
    public Element root;

    public Document(String attribute) {
        root = new Element();
        root.attribute = attribute;
        root.elements = new ArrayList<>();
    }

    public Iterator<String> iterator() {
        return new DocumentIterator(root);
    }
}

class Element {
    public String attribute;
    public List<Element> elements;

}

class DocumentIterator implements Iterator<String>{

    Stack<Element> stack = new Stack<>();
    public DocumentIterator(Element root){
        stack.push(root);
    }
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public String next() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        Element ele = stack.pop();
        for(Element e: ele.elements){
            stack.push(e);
        }
        return ele.attribute;
    }

}