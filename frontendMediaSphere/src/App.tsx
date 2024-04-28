import { useState, useEffect } from 'react';
import axios from 'axios';
import { Container, Typography, TextField, Button, List, ListItem, ListItemText, ListItemSecondaryAction, IconButton, Dialog, DialogTitle, DialogContent, DialogActions } from '@mui/material';
import { Delete, Edit } from '@mui/icons-material';

function App() {
  const [mediaList, setMediaList] = useState([]);
  const [formData, setFormData] = useState({ title: '', description: '', type: '' });
  const [selectedMedia, setSelectedMedia] = useState(null);
  const [openDialog, setOpenDialog] = useState(false);

  useEffect(() => {
    fetchMedia();
  }, []);

  const fetchMedia = async () => {
    try {
      const response = await axios.get('http://localhost:8080/media');
      setMediaList(response.data);
    } catch (error) {
      console.error('Error fetching media:', error);
    }
  };

  const handleInputChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8080/media', formData);
      setFormData({ title: '', description: '', type: '' });
      fetchMedia(); // Refresh media list after adding new media
    } catch (error) {
      console.error('Error creating media:', error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/media/${id}`);
      fetchMedia(); // Refresh media list after deleting media
    } catch (error) {
      console.error('Error deleting media:', error);
    }
  };

  const handleUpdate = async (id, updatedData) => {
    try {
      await axios.put(`http://localhost:8080/media/${id}`, updatedData);
      fetchMedia(); // Refresh media list after updating media
    } catch (error) {
      console.error('Error updating media:', error);
    }
  };

  const handleOpenDialog = (media) => {
    setSelectedMedia(media);
    setOpenDialog(true);
  };

  const handleCloseDialog = () => {
    setSelectedMedia(null);
    setOpenDialog(false);
  };

  return (
    <Container>
      <Typography variant="h1">Media List</Typography>
      <List>
        {mediaList.map((media) => (
          <ListItem key={media.id} button onClick={() => handleOpenDialog(media)}>
            <ListItemText primary={media.title} secondary={media.description} />
            <ListItemSecondaryAction>
              <IconButton onClick={() => handleDelete(media.id)}>
                <Delete />
              </IconButton>
              <IconButton onClick={() => handleUpdate(media.id)}>
                <Edit />
              </IconButton>
            </ListItemSecondaryAction>
          </ListItem>
        ))}
      </List>

      <Typography variant="h2">Add Media</Typography>
      <form onSubmit={handleSubmit}>
        <TextField label="Title" name="title" value={formData.title} onChange={handleInputChange} fullWidth />
        <TextField label="Description" name="description" value={formData.description} onChange={handleInputChange} fullWidth />
        <TextField label="Type" name="type" value={formData.type} onChange={handleInputChange} fullWidth />
        <Button type="submit" variant="contained" color="primary">Add</Button>
      </form>

      <Dialog open={openDialog} onClose={handleCloseDialog}>
        <DialogTitle>{selectedMedia && selectedMedia.title}</DialogTitle>
        <DialogContent>
          <Typography>{selectedMedia && selectedMedia.description}</Typography>
          <Typography>{selectedMedia && selectedMedia.type}</Typography>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => handleDelete(selectedMedia.id)}>Delete</Button>
          <Button onClick={() => handleUpdate(selectedMedia.id)}>Update</Button>
          <Button onClick={handleCloseDialog}>Close</Button>
        </DialogActions>
      </Dialog>
    </Container>
  );
}

export default App;
