import React, { useEffect, useState } from 'react';
import axios from 'axios';

const ImageListComponent = () => {
    const [images, setImages] = useState([]);

    useEffect(() => {
        const fetchImages = async () => {
            try {
                const response = await axios.get('http://localhost:8080/image');
                setImages(response.data);
            } catch (error) {
                console.error('Erro ao buscar imagens:', error);
            }
        };

        fetchImages();
    }, []);

    return (
        <div>
            <h2>Lista de Imagens</h2>
            <div className="image-list">
                {images.map(image => (
                    <div key={image.id} className="image-item">
                        <img src={`http://localhost:8080/image/${image.id}`} alt={image.name} style={{ width: '200px', height: '200px' }} />
                        <p>{image.name}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ImageListComponent;
