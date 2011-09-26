Sesion support

1) Action extends AbstractSecureAction
2) Reuslt extends SecureResult
3) Server side
ConcreteSecureHandler extends AbstractSecureActionHandler
{
	protected abstract R executeIntenal(A action,
			ExecutionContext executioncontext){
	
	 R result = new R();
	 if(!isValidLogin(action)){
	 	return result;
	 }
	 
	 //TODO: do you you want to do.
	 return result;		
	}
}