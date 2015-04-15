package pl.wasat.smarthma.utils.text;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RecursiveToSrtingStyle extends ToStringStyle {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> recursivePackags;
    private int recursiveLimit = 10;

    public RecursiveToSrtingStyle(String... pkgs) {
        super();
        recursivePackags = Arrays.asList(pkgs);
        //this.setUseShortClassName(true);
        this.setUseIdentityHashCode(false);
    }

    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
        for(String packaz : recursivePackags) {
            if (value.getClass().getCanonicalName().startsWith(packaz)) {
                buffer.append(ToStringBuilder.reflectionToString(value, this));
                return;
            }
        }
        buffer.append(value);
    }
    
    
    
    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, @SuppressWarnings("rawtypes") Map map) {
        //super.appendDetail(buffer, fieldName, map);
    	buffer.append(getArrayStart());
        int count = 0;
        for(Entry<?, ?> entry : ((Map<? ,?>)map).entrySet()) {
            appendDetail(buffer, fieldName, entry.getKey());
            buffer.append(getFieldNameValueSeparator());
            appendDetail(buffer, fieldName, entry.getValue());
            appendFieldSeparator(buffer);
            if((count++) > recursiveLimit) {
                buffer.append("...");
                break;
            }
        }
        removeLastFieldSeparator(buffer);
        buffer.append(getArrayEnd());
    }

	@Override
    protected void appendDetail(StringBuffer buffer, String fieldName, @SuppressWarnings("rawtypes") Collection coll) {
        appendContentStart(buffer);
        int count = 0;
        for(Object o : coll){
            appendDetail(buffer, fieldName, o);
            appendFieldSeparator(buffer);
            if((count++) > recursiveLimit) {
                buffer.append("...");
                break;
            }
        }
        removeLastFieldSeparator(buffer);
        appendContentEnd(buffer);
    }
}
