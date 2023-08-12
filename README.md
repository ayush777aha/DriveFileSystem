Database Schema Design:

User Table:

user_id (Primary Key)
username
email
password_hash
Node Table:

node_id (Primary Key)
parent_id (Foreign Key referencing the parent node)
user_id (Foreign Key referencing the user who owns the node)
name (Name of the node)
is_folder (Boolean indicating whether the node is a folder or a file)
created_at
updated_at
File Table:

file_id (Primary Key)
node_id (Foreign Key referencing the associated node)
content_type (MIME type of the file)
size (Size of the file in bytes)
storage_path (Path to the stored file)

