library(igraph) 
library(ggplot2)
library(ggraph)
library(tidygraph)
library(ggforce)
library(concaveman)
library(graphlayouts)
library(oaqc)
library(sna)
library(dplyr)
library(viridis)
library(ggsci)
library(graphlayouts) # backbone layout

# rm(list=ls())
setwd(dirname(rstudioapi::getSourceEditorContext()$path)) # Set SRC DIR as WD
fig_dpi <- 600

fig_width <- 1.75
fig_height <- 1.75
node_size <- 1
node_stroke <- .1
edge_width <- .1

# fig_width <- 4
# fig_height <- 4
# fig_width <- 2.5
# fig_height <- 1.5
# fig_width <- 1.5
# fig_height <- 3

edge_alpha <- .75

files2print <- function(list_file){
  for( file in list_file){
    dat <- read.csv(file)
    l <- dat$L[1]
    l_range <- (1:l)-1
    
    net_node <- distinct(dat[,c('SOURCE', paste0('SOURCE_TYPE', l_range))])
    colnames(net_node) <- c('id', paste0('type', l_range))
    # net_node$color <- ifelse(net_node$type=='[true]','black','gray75')
    if (dat$L[1] == 1){
      net_node$type <- factor(net_node$type0)
    }else{
      net_node$type <- factor(apply(net_node[,paste0('type', l_range)], MARGIN=1, paste, collapse=''))
    }
    net_edge <- distinct(dat[,c('SOURCE', 'TARGET', 'TIE_ENFORCED')])
    net_edge <- na.omit(net_edge)
    colnames(net_edge) <- c('from', 'to', 'is_enforced')
    
    # V(net)$label <- NA
    
    net <- graph_from_data_frame(
      vertices = net_node,
      d = net_edge,
      directed = F
    )
    set_vertex_attr(net, 'ec', value = eigen_centrality(net)$vector)
    
    rm(list = c('layout'))
    # layout <- create_layout(net, layout = 'fr')
    
    # layout <- create_layout(net, 'tree', root = 1)
    # layout <- create_layout(net, 'tree', root = 1, circular = T)
    
    # net_formal <- net
    # net_formal <- delete_edges(net_formal, which(E(net_formal)$is_enforced=="false"))
    # layout <- create_layout(net_formal, 'tree', root = 1)
    # layout <- data.frame(x = layout$x, y = layout$y)
    # layout2 <- create_layout(net, 'tree', root = 1)
    
    # layout <- create_layout(net, layout = 'lgl')
    # layout <- create_layout(net, layout = 'graphopt')
    # layout <- create_layout(net, layout = 'stress')
    # layout <- create_layout(net, layout = 'auto')
    layout <- create_layout(net, layout = 'nicely') # -->   fr
    # layout <- create_layout(net, layout = 'backbone', keep=.1)
    
    pp <- ggraph( net, layout = layout ) +
    # pp <- ggraph( net, layout = 'manual', x = bb$xy[,1], y= bb$xy[,2] ) +
      # geom_mark_hull(
      #   aes(
      #     x = layout$x,
      #     y = layout$y,
      #     group = net_node$type,
      #     fill = net_node$type,
      #   ),
      #   color = NA,
      #   concavity = 4,
      #   expand = unit(2, 'mm')
      # ) +
      scale_fill_npg() +
      # geom_edge_diagonal(
      # geom_edge_arc(
      geom_edge_link(
        aes(
          color = net_edge$is_enforced,
          linetype = net_edge$is_enforced
        ),
        # color = 'black',
        width = edge_width,
        alpha = edge_alpha
      ) +
      scale_edge_color_manual(breaks=c('true','false'), values=c('black', '#9B0000')) +
      scale_edge_linetype_manual(breaks=c('true','false'), values=c('solid', 'dashed')) +
      geom_node_point(
        aes(
          shape = net_node$type,
          fill = net_node$type
        ),
        shape = 1,
        color='black',
        size=node_size,
        stroke=node_stroke) +
      scale_shape_manual(values = c(0, 1, 12, 10)) +
      theme_void() +
      theme(
        legend.position = 'none',
        plot.margin=grid::unit(c(0,0,0,0), 'mm')
      )
    
    pp
    
    file_out <- paste0(
      dirname(file), '\\',
      gsub('csv', 'png', basename(file))
    )
    
    ggsave(
     file_out,
     units = 'in', 
     width = fig_width, 
     height = fig_height, 
     dpi = fig_dpi
    )
    
    print(file_out)
  }
  
}

files2print(choose.files())