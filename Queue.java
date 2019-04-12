

public class Queue<T> implements QueueInterface<T>{

	protected T[] items;
	protected int front;
	protected int back;
	private int numItems;



	@SuppressWarnings("unchecked")
	public Queue() {
		items = (T[]) new Object[3];
		front = 0;
		back = 0;
		numItems = 0;
	}
	
	protected void resize()
	{
		@SuppressWarnings("unchecked")
		T[] newQueue = (T[]) new Object[numItems*(3/2)+1];
		for(int i = 0; i < items.length; i++)
		{
			newQueue[i] = items[(front + i)%items.length];
		}
		front = 0;
		back = numItems;
		items = newQueue;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return numItems == 0;
	}

	@Override
	public void enqueue(T item) throws QueueException {
		if(numItems == items.length)
		{
			resize();
		}
		items[back] = item;
		back = (back + 1)%items.length;
		numItems++;

	}

	@Override
	public T dequeue() throws QueueException {
		T result;
		if(isEmpty())
		{
			throw new QueueException("Queue is empty");
		}
		else
		{
			items[front] = null;
			front = (front + 1)%items.length;
			result = items[front];
			numItems--;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void dequeueAll() {
		items = (T[]) new Object[3];
		front = 0;
		back = 0;
		numItems = 0;
	}

	@Override
	public T peek() throws QueueException {
		if(isEmpty()) {
			throw new QueueException("Queue is empty.");
		}
		return items[front];
	}
	
	public String toString()
	{
		String str = "";
		int size = items.length;
		for(int i = 0; i < size; i++)
		{
			if(items[i] != null) 
			{
				str += " " + items[i].toString();
			}
		}
		return str;
	}
	
	public int getNumItems() {
		return numItems;
	}

}
