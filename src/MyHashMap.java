public class MyHashMap <K, V> {
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Массив корзин
    private Node<K, V>[] buckets;
    private int size;
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    // Конструктор
    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = new Node[INITIAL_CAPACITY];
        size = 0;
    }

    // Вычисление индекса корзины
    private int getIndex(K key, int length) {
        int hash = key == null ? 0 : key.hashCode();
        return Math.abs(hash) % length;
    }

    // Рехэширование: увеличение массива и перераспределение элементов
    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] oldBuckets = buckets;
        int newCapacity = oldBuckets.length * 2;
        buckets = new Node[newCapacity];
        size = 0; // Сбрасываем, так как put пересчитает

        // Перераспределяем все элементы
        for (Node<K, V> bucket : oldBuckets) {
            Node<K, V> current = bucket;
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }

    // Метод put: добавление или обновление пары ключ-значение
    public void put(K key, V value) {
        // Проверяем необходимость рехэширования
        if (size >= buckets.length * LOAD_FACTOR) {
            resize();
        }

        int index = getIndex(key, buckets.length);

        // Если корзина пуста, создаём новый узел
        if (buckets[index] == null) {
            buckets[index] = new Node<>(key, value);
            size++;
            return;
        }

        // Проверяем список в корзине
        Node<K, V> current = buckets[index];
        Node<K, V> prev = null;

        while (current != null) {
            // Если ключ уже существует, обновляем значение
            if ((key == null && current.key == null) ||
                    (key != null && key.equals(current.key))) {
                current.value = value;
                return;
            }
            prev = current;
            current = current.next;
        }

        // Добавляем новый узел в конец списка
        prev.next = new Node<>(key, value);
        size++;
    }

    // Метод get: получение значения по ключу
    public V get(K key) {
        int index = getIndex(key, buckets.length);
        Node<K, V> current = buckets[index];

        // Ищем ключ в списке
        while (current != null) {
            if ((key == null && current.key == null) ||
                    (key != null && key.equals(current.key))) {
                return current.value;
            }
            current = current.next;
        }
        return null; // Ключ не найден
    }

    // Метод remove: удаление пары по ключу
    public boolean remove(K key) {
        int index = getIndex(key, buckets.length);
        Node<K, V> current = buckets[index];
        Node<K, V> prev = null;

        // Ищем ключ
        while (current != null) {
            if ((key == null && current.key == null) ||
                    (key != null && key.equals(current.key))) {
                // Удаляем узел
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false; // Ключ не найден
    }

    // Размер HashMap
    public int size() {
        return size;
    }
}
