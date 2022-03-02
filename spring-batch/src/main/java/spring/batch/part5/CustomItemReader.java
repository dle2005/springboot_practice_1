package spring.batch.part5;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.ArrayList;
import java.util.List;

public class CustomItemReader<T> implements ItemReader<T> {

    private final List<T> items;

    public CustomItemReader(List<T> items) {
        this.items = new ArrayList<>(items);
    }

    @Override
    public T read() {
        if (!items.isEmpty()) {
            return items.remove(0);
        }

        return null; // null 이 리턴 되면 chunk 반복이 끝남
    }
}
