library(igraph)

simulate_caveman <- function(num_group = 9, within_group = 10){
  n = num_group * within_group
  clique_size = within_group
  groups = num_group
  el <- data.frame(PersonA = 1:n, Group = NA) # I create a dataframe which has people and the groups they are in
  # I treat it like a person to group edgelist
  group_vector = c()
  for (i in 1:groups){
    group_vector <- c(group_vector, rep(i, clique_size))
  }  
  
  el$Group <- group_vector
  inc <- table(el) # I use the table function to turn the person to group edgelist into an incidence matrix
  adj <- inc %*% t(inc) # And I use matrix multiplication with the transpose to turn the person to group incidence matrix
  # into a person to person adjacency matrix
  diag(adj) <- 0 
  g <- graph.adjacency(adj, mode = "undirected") # I graph this matrix
  group_connect <- seq(from = 1, to = n, by = clique_size) # I determine the points of connection using a sequence funciton
  for( i in 1:(length(group_connect)-1)){
    p1 <- group_connect[i] + 1
    p2 <- group_connect[i+1]
    g <- add.edges(g, c(p1,p2)) # And I connect the points of connection using add.edges
  }
  g <- add.edges(g, c(group_connect[1],(group_connect[groups]+1))) # finally I connect the ends of the structure so that it forms a circle
  return(g)    
}

simulate_metric <- function(p){
  net <- simulate_caveman() %>% rewire(each_edge(p, loops=F))
  return( c(
    p = p,
    b = var(betweenness(net)),
    t = transitivity(net)
  ))
}

res 

res <- mapply(seq(from=0, to=1, length.out =21), FUN=simulate_metric)
res <- as.data.frame(t(res))

res$b <- log(res$b)
res$t <- log(res$t)

ggplot(res, aes(x = p)) +
  geom_line(aes(y = b, color = 'Betweenness'))+
  geom_line(aes(y = t, color = 'Transitivity'))