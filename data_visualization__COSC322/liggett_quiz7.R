library(igraph)
library(tidyverse)



cosc_req <- tibble(id = c("COSC 130", "COSC 210", "COSC 211", "COSC 235", "COSC 260", "COSC 310",
                          "COSC 327", "COSC 330", "COSC 350", "MATH 151", "MATH 320", "MATH 315"))

cosc_elec <- tibble(id = c("COSC 225",			
                           "COSC 226",
                           "COSC 215",
                           "COSC 315",
                           "COSC 322",
                           "COSC 342",
                           "COSC 380",
                           "COSC 212",
                           "COSC 320",
                           "COSC 180",
                           "COSC 397"))

dasc_req <- tibble(id = c(
  "COSC 210", "COSC 211", "COSC 212", "COSC 215", "COSC 315", "COSC 322",
  "MATH 151", "MATH 152", "MATH 315", "MATH 320", "MATH 327" 
))

dasc_elec <- tibble(id = c(
  "COSC 241",
  "COSC 342",
  "COSC 260",
  "COSC 380",
  "MATH 220",
  "MATH 345",
  "MATH 397",
  "COSC 397"
))



cis_req <- tibble(id = c(
  "COSC 130", "COSC 205", "COSC 210", "COSC 211", "COSC 241", "COSC 315",
  "COSC 342", "BSAD 320", "ECON 120", "ECON 270" 
))

cis_elec <- tibble(id = c(
  "ACCT 207",
  "ACCT 210",
  "BSAD 310",
  "BSAD 330",
  "COSC 235",
  "COSC 260",
  "COSC 225",
  "COSC 397"
))


math_req <- tibble(id = c(
  "MATH 151", "MATH 152", "MATH 153", "MATH 200", "MATH 220", "MATH 490",
  "COSC 210", "PHYS 221", "MATH 330", "MATH 335", "MATH 397"
))

math_elec <- tibble(id = c(
  "MATH 340",
  "MATH 345",
  "MATH 350",
  "MATH 355",
  "MATH 310",
  "MATH 315",
  "MATH 320",
  "MATH 327"
))

phys_req <- tibble(id = c(
  "PHYS 221", "PHYS 222", "PHYS 281", "PHYS 321", "PHYS 371", "PHYS 381",
  "MATH 151", "MATH 152", "MATH 153", "MATH 310", "PHYS 399"
))

phys_elec <- tibble(id = c(
  "PHYS 331",
  "PHYS 341",
  "PHYS 351",
  "PHYS 361",
  "PHYS 363",
  "PHYS 372",
  "PHYS 373",
  "PHYS 391",
  "PHYS 395",
  "PHYS 397"
))

bsad_req <- tibble(id = c(
  "ACCT 210", "ACCT 211", "COSC 205", "ECON 270", "BSAD 310", "BSAD 320",
  "BSAD 330", "BSAD 340", "BSAD 382", "BSAD 421"
))

bsad_elec <- tibble(id  = c(
  "BSAD 490",
  "BSAD 495",
  "BSAD 499",
  "ECON 120",
  "ECON 121"
))

# Joins

cosc <- full_join(cosc_req, cosc_elec)
dasc <- full_join(dasc_req, dasc_elec)
cis <- full_join(cis_req, cis_elec)
math <- full_join(math_req, math_elec)
bsad <- full_join(bsad_req, bsad_elec)
phys <- full_join(phys_req, phys_elec)

courses <- full_join(cosc, dasc)
courses <- full_join(courses, cis)
courses <- full_join(courses, math)
courses <- full_join(courses, bsad)
courses <- full_join(courses, phys)

node_list <- full_join(tibble(id = c("MATH", "DASC", "COSC", "CIS", "BSAD", "PHYS")), courses)

# Node lists for each major
math_node_list <- full_join(tibble(id = c("MATH")), math_req)
cosc_node_list <- full_join(tibble(id = c("COSC")), cosc_req)
cis_node_list <- full_join(tibble(id = c("CIS")), cis_req)
bsad_node_list <- full_join(tibble(id = c("BSAD")), bsad_req)
dasc_node_list <- full_join(tibble(id = c("DASC")), dasc_req)
phys_node_list <- full_join(tibble(id = c("PHYS")), phys_req)

# Edge lists for each major
math_edge_list <- tibble(from = c("MATH"), to = math_req$id)
cosc_edge_list <- tibble(from = c("COSC"), to = cosc_req$id)
cis_edge_list <- tibble(from = c("CIS"), to = cis_req$id)
bsad_edge_list <- tibble(from = c("BSAD"), to = bsad_req$id)
dasc_edge_list <- tibble(from = c("DASC"), to = dasc_req$id)
phys_edge_list <- tibble(from = c("PHYS"), to = phys_req$id)

# Creates a full edge list
edge_list_1 <- full_join(math_edge_list, cosc_edge_list)
edge_list_2 <- full_join(cis_edge_list, bsad_edge_list)
edge_list_3 <- full_join(dasc_edge_list, phys_edge_list)
edge_list_4 <- full_join(edge_list_1, edge_list_2)
edge_list <- full_join(edge_list_4, edge_list_3)
