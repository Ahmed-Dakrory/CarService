function deletePdfNum(filename){
	                                		swal("Are You Sure You want To Delete the Pdf file?", {
	                                			  buttons: {
	                                			    cancel: "Cancel",
	                                			    catch: {
	                                			      text: "Yes Sure, Delete it",
	                                			      value: "catch",
	                                			    }
	                                			  },
	                                			})
	                                			.then((value) => {
	                                			  switch (value) {
	                                			 
	                                			 
	                                			    case "catch":
	                                			    var dataNewOfString = filename;
	                                			      deleteFile([{name: 'fileURL', value: dataNewOfString}, {name: 'typeOfFile', value: '2'}]);
	                                			      break;
	                                			 
	                                			    default:
	                                			      
	                                			  }
	                                			});
	                                	}
	                                	
	                                	
	                                	
	                                	
function deleteImageNum(filename){
	                                		swal("Are You Sure You want To Delete this Image?", {
	                                			  buttons: {
	                                			    cancel: "Cancel",
	                                			    catch: {
	                                			      text: "Yes Sure, Delete it",
	                                			      value: "catch",
	                                			    }
	                                			  },
	                                			})
	                                			.then((value) => {
	                                			  switch (value) {
	                                			 
	                                			 
	                                			    case "catch":
	                                			    var dataNewOfString = filename;
	                                			      deleteFile([{name: 'fileURL', value: dataNewOfString}, {name: 'typeOfFile', value: '0'}]);
	                                			      break;
	                                			 
	                                			    default:
	                                			      
	                                			  }
	                                			});
	                                	}
	                                	
	                                	
	       	                                	                         	
function deleteDocsNum(filename){
	                                		swal("Are You Sure You want To Delete the Document?", {
	                                			  buttons: {
	                                			    cancel: "Cancel",
	                                			    catch: {
	                                			      text: "Yes Sure, Delete it",
	                                			      value: "catch",
	                                			    }
	                                			  },
	                                			})
	                                			.then((value) => {
	                                			  switch (value) {
	                                			 
	                                			 
	                                			    case "catch":
	                                			    var dataNewOfString = filename;
	                                			      deleteFile([{name: 'fileURL', value: dataNewOfString}, {name: 'typeOfFile', value: '1'}]);
	                                			      break;
	                                			 
	                                			    default:
	                                			      
	                                			  }
	                                			});
	                                	}	         
	                                	
	                                	
	

	
function deleteThisProduct(productId){
	                                		swal("Are You Sure You want To Delete this product?", {
	                                			  buttons: {
	                                			    cancel: "Cancel",
	                                			    catch: {
	                                			      text: "Yes Sure, Delete it",
	                                			      value: "catch",
	                                			    }
	                                			  },
	                                			})
	                                			.then((value) => {
	                                			  switch (value) {
	                                			 
	                                			 
	                                			    case "catch":
	                                			    var dataNewOfString = productId;
	                                			    deleteProduct([{name: 'productId', value: dataNewOfString}]);
	                                			      break;
	                                			 
	                                			    default:
	                                			      
	                                			  }
	                                			});
	                                	}	                                	
	                                		       	                                	                         	
function deleteThisCar(carId){
	                                		swal("Are You Sure You want To Delete this car?", {
	                                			  buttons: {
	                                			    cancel: "Cancel",
	                                			    catch: {
	                                			      text: "Yes Sure, Delete it",
	                                			      value: "catch",
	                                			    }
	                                			  },
	                                			})
	                                			.then((value) => {
	                                			  switch (value) {
	                                			 
	                                			 
	                                			    case "catch":
	                                			    var dataNewOfString = carId;
	                                			      deleteCar([{name: 'carId', value: dataNewOfString}]);
	                                			      break;
	                                			 
	                                			    default:
	                                			      
	                                			  }
	                                			});
	                                	}	                                	