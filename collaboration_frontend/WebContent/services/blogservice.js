/**
 * BlogService
 */
app.factory('BlogService',function($http){
	var blog={}
		var BASE_URL="http://localhost:9090/collaboration_middle"
			
			blog.addBlog=function(blog){
			return $http.post(BASE_URL + "/addblogpost",blog)
		}
		blog.getBlogsWaitingForApproval=function(){
			return $http.get(BASE_URL + "/getblogs/"+false)
		}
		
		blog.getApprovedBlogs=function(){
			return $http.get(BASE_URL + "/getblogs/"+true)
		}
		
		blog.getBlog=function(id){
			return $http.get(BASE_URL + "/getblog/"+id)
		}
		return blog;
})





