package tree;


public class AVLNode<T extends Comparable> {
	
	 	public AVLNode<T> left;//左结点

	    public AVLNode<T> right;//右结点

	    public T data;

	    public int height;//当前结点的高度

	    public AVLNode(T data) {
	        this(null,null,data);
	    }

	    public AVLNode(AVLNode<T> left, AVLNode<T> right, T data) {
	        this(left,right,data,0);
	    }

	    public AVLNode(AVLNode<T> left, AVLNode<T> right, T data, int height) {
	        this.left=left;
	        this.right=right;
	        this.data=data;
	        this.height = height;
	    }
	    //LL左左单旋转 (在失衡节点的左节点的左子树上插入）
	    private AVLNode<T> singleRotateLeft(AVLNode<T> x){
	    	AVLNode<T> w = x.left;
	    	x.left = w.right;
	    	w.right = x;
	    	//重新计算高度
	    	x.height = Math.max(height(x.left), height(x.right))+1;
	    	w.height = Math.max(height(w.left), height(w.right))+1;
	    	return w;
	    }
	    //RR右右单旋转(在失衡节点的右节点的右子树上插入）
	    private AVLNode<T> singleRotateRight(AVLNode<T> x){
	    	AVLNode<T> w = x.right;
	    	x.right = w.left;
	    	w.left = x;
	    	//重新计算高度
	    	x.height = Math.max(height(x.left), height(x.right))+1;
	    	w.height = Math.max(height(w.left), height(w.right))+1;
	    	return w;
	    	
	    }
	    /**
	     * 左右旋转(LR旋转) 
	     * @return
	     */
	    private AVLNode<T> doubleRotateWithLeft(AVLNode<T> x){
	        //w先进行RR旋转
	        x.left=singleRotateRight(x.left);
	        //再进行x的LL旋转
	        return singleRotateLeft(x);
	    }
	    
	    private AVLNode<T> doubleRotateWithRight(AVLNode<T> x){
	        //先进行LL旋转
	        x.right=singleRotateLeft(x.right);
	        //再进行RR旋转
	        return singleRotateRight(x);
	    }
	    
	    private int height(AVLNode<T> node){
	    	if(node == null){
	    		return -1;
	    	}else {
				return node.height;
			}
	    }
	    
	    //向以p为根节点的平衡树中插入节点
	    private AVLNode<T> insert(T data , AVLNode<T> p){

	    	   //说明已没有孩子结点,可以创建新结点插入了.
	    	   if(p==null){
	    	       p=new AVLNode<T>(data);
	    	   }else if(data.compareTo(p.data)<0){//向左子树寻找插入位置
	    	       p.left=insert(data,p.left);

	    	       //插入后计算子树的高度,等于2则需要重新恢复平衡,由于是左边插入,左子树的高度肯定大于等于右子树的高度
	    	       if(height(p.left)-height(p.right)==2){
	    	           //判断data是插入点的左孩子还是右孩子
	    	           if(data.compareTo(p.left.data)<0){
	    	               //进行LL旋转
	    	               p=singleRotateLeft(p);
	    	           }else {
	    	               //进行左右旋转
	    	               p=doubleRotateWithLeft(p);
	    	           }
	    	       }
	    	   }else if (data.compareTo(p.data)>0){//向右子树寻找插入位置
	    	       p.right=insert(data,p.right);

	    	       if(height(p.right)-height(p.left)==2){
	    	           if (data.compareTo(p.right.data)<0){
	    	               //进行右左旋转
	    	               p=doubleRotateWithRight(p);
	    	           }else {
	    	               p=singleRotateRight(p);
	    	           }
	    	       }
	    	   }
	    	   else
	    	    ;//if exist do nothing
	    	   //重新计算各个结点的高度
	    	   p.height = Math.max( height( p.left ), height( p.right ) ) + 1;

	    	   return p;//返回根结点
	    	}
	    
	    //删除操作	
	    private AVLNode<T> remove(T data,AVLNode<T> p){
	        if(p ==null)
	            return null;
	        int result=data.compareTo(p.data);

	        //从左子树查找需要删除的元素
	        if(result<0){
	            p.left=remove(data,p.left);

	            //检测是否平衡
	            if(height(p.right)-height(p.left)==2){
	                AVLNode<T> currentNode=p.right;
	                //判断需要那种旋转
	                if(height(currentNode.left)>height(currentNode.right)){
	                    //LL
	                    p=singleRotateLeft(p);
	                }else{
	                    //LR
	                    p=doubleRotateWithLeft(p);
	                }
	            }

	        }
	        //从右子树查找需要删除的元素
	        else if(result>0){
	            p.right=remove(data,p.right);
	            //检测是否平衡
	            if(height(p.left)-height(p.right)==2){
	                AVLNode<T> currentNode=p.left;
	                //判断需要那种旋转
	                if(height(currentNode.right)>height(currentNode.left)){
	                    //RR
	                    p=singleRotateRight(p);
	                }else{
	                    //RL
	                    p=doubleRotateWithRight(p);
	                }
	            }
	        }
	        //已找到需要删除的元素,并且要删除的结点拥有两个子节点
	        else if(p.right!=null&&p.left!=null){

	            //寻找替换结点
	            p.data=findMin(p.right).data;

	            //移除用于替换的结点
	            p.right = remove( p.data, p.right );
	        }
	        else {
	            //只有一个孩子结点或者只是叶子结点的情况
	            p=(p.left!=null)? p.left:p.right;
	        }

	        //更新高度值
	        if(p!=null)
	            p.height = Math.max( height( p.left ), height( p.right ) ) + 1;
	        return p;
	    }

		private AVLNode<T> findMin(AVLNode<T> node) {
			if(node.left == null){
				return node;
			}
			return findMin(node.left);
		}
	    
	   
}
