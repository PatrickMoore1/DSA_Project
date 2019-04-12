
// Please note that this code is slightly different from the textbook code 
//to reflect the fact that the Node class is implemented using data encapsulation


// ****************************************************
// Reference-based implementation of ADT list.
// ****************************************************
public class MyListReferenceBased<T> implements ListInterface<T> 
{
	// reference to linked list of items
	private Node<T> head; 

	public MyListReferenceBased() 
	{
		head = null;
	}  // end default constructor

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
	}  // end isEmpty
	
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
	}  // end size

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
		// --------------------------------------------------
		// Locates a specified node in a linked list.
		// Precondition: index is the number of the desired
		// node. Assumes that 0 <= index <= numItems 
		// Postcondition: Returns a reference to the desired 
		// node.
		// --------------------------------------------------
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
			} // end for
		}
		return curr;
	} // end find

	public T get(int index) 
			throws ListIndexOutOfBoundsException 
	{
		if (isIndexValid(index)) 
		{
			// get reference to node, then data in node
			Node<T> curr = find(index);
			T dataItem = curr.getItem();
			return dataItem;
		} 
		else 
		{
			throw new ListIndexOutOfBoundsException(
					"List index out of bounds exception on get");

		} // end if
	} // end get

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void add(int index, T item)
			throws ListIndexOutOfBoundsException 
	{
		if (isIndexValid(index)) 
		{
			if (index == 0) 
			{
				// insert the new node containing item at
				// beginning of list
				Node<T> newNode = new Node(item, head);
				head = newNode;
			} 
			else 
			{
				Node<T> prev = find(index-1);
				// insert the new node containing item after 
				// the node that prev references
				Node<T> newNode = new Node(item, prev.getNext());
				prev.setNext(newNode);
			} // end if
		} 
		else 
		{
			throw new ListIndexOutOfBoundsException(
					"List index out of bounds exception on add");
		} // end if
	}  // end add

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
					// delete the first node from the list
					head = head.getNext();
				} 
				else 
				{
					Node<T> prev = find(index-1);
					// delete the node after the node that prev
					// references, save reference to node
					Node<T> curr = prev.getNext(); 
					prev.setNext(curr.getNext());
				} // end if
			} // end if
			else 
			{
				System.out.println("Position specified is out of range!");
				throw new ListIndexOutOfBoundsException(
						"List index out of bounds exception on remove");
			} // end if

		}
	}   // end remove

	public void removeAll() 
	{
		if(isEmpty())
		{
			System.out.println("List empty... Nothing to remove.");
		}
		else								// setting head to null causes list to be
		{									// unreachable and thus marked for garbage 
			head = null;					// collection
		}

	} // end removeAll
	
	public Node<T> getHead()
	{
		return head;
	}
} // end ListReferenceBased