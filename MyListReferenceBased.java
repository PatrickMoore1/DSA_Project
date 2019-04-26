
public class MyListReferenceBased<T> implements ListInterface<T> 
{
	private Node<T> head; 

	public MyListReferenceBased() 
	{
		head = null;
	}  

	private boolean isIndexValid(int index)
	{
		boolean isValid = true;
		if(index < 0 || index > size() )
		{
			isValid = false;
		}
		return isValid;
	}

	public boolean isEmpty() 
	{
		boolean isEmpty = false;
		int size = size();
		if(size == 0)
		{
			isEmpty = true;
		}
		return isEmpty;
	} 

	public int size() 
	{
		int numItems = 0;
		Node<T> curr = head;
		while(curr != null)
		{
			numItems++;
			curr = curr.getNext();
		}
		return numItems;
	} 

	public String toString()
	{
		String start = " ";
		Node<T> curr = head;
		int size = size();
		if(isEmpty())
		{
			start = "List is empty.";
		}
		else
		{
			start = "List of size " + size +" has the following items: ";
			for(int i = 0; i < size; i++)
			{
				start += " " + curr.getItem().toString();
				curr = curr.getNext();
			}
		}
		return start;
	}

	private Node<T> find(int index) 
	{
		Node<T> curr = head;
		if(!isIndexValid(index))
		{
			System.out.println("OUT OF BOUNDS");
		}
		else
		{
			for (int i = 0; i < index; i++) 
			{
				curr = curr.getNext();
			}
		}
		return curr;
	}

	public T get(int index) 
			throws ListIndexOutOfBoundsException 
	{
		if (isIndexValid(index)) 
		{
			Node<T> curr = find(index);
			T dataItem = curr.getItem();
			return dataItem;
		} 
		else 
		{
			throw new ListIndexOutOfBoundsException(
					"List index out of bounds exception on get");

		} 
	} 

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void add(int index, T item)
			throws ListIndexOutOfBoundsException 
	{
		if (isIndexValid(index)) 
		{
			if (index == 0) 
			{
				Node<T> newNode = new Node(item, head);
				head = newNode;
			} 
			else 
			{
				Node<T> prev = find(index-1);
				Node<T> newNode = new Node(item, prev.getNext());
				prev.setNext(newNode);
			} // end if
		} 
		else 
		{
			throw new ListIndexOutOfBoundsException(
					"List index out of bounds exception on add");
		}
	}  

	public void remove(int index) 
			throws ListIndexOutOfBoundsException 
	{
		if(isEmpty())
		{
			System.out.println("List is empty... Nothing to remove.");
		}
		else
		{
			if (isIndexValid(index)) 
			{
				if (index == 0) 
				{
					head = head.getNext();
				} 
				else 
				{
					Node<T> prev = find(index-1);
					Node<T> curr = prev.getNext(); 
					prev.setNext(curr.getNext());
				} 
			} 
			else 
			{
				System.out.println("Position specified is out of range!");
				throw new ListIndexOutOfBoundsException(
						"List index out of bounds exception on remove");
			}

		}
	}   

	public void removeAll() 
	{
		if(isEmpty())
		{
			System.out.println("List empty... Nothing to remove.");
		}
		else								
		{									 
			head = null;				
		}

	} 

	public Node<T> getHead()
	{
		return head;
	}

	public void add(Customer customer) {
		if (size() == 0) 
		{
			Node<T> newNode = new Node(customer, head);
			head = newNode;
		} 
		else 
		{
			Node<T> node = head;
			while(node.getNext() != null) {
				node = node.getNext();
			}
			node.setNext(new Node(customer, null));
		} 
	}
	
	public boolean findLineCustomer(String name) {
		Node<T> node = head;
		
		boolean result = false;
		while(node != null && !result) {
			if(name.equalsIgnoreCase(((Customer) node.getItem()).getName())) {
				result = true;				
			} else {
				node = node.getNext();
			}
		}
		
		return result;
		
	}

}


