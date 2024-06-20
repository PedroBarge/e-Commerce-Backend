import React, { useState } from 'react';
import axios from 'axios';

const UploadImageComponent = () => {
    const [file, setFile] = useState(null);

    const handleFileChange = (e) => {
        setFile(e.target.files[0]);
    };

    const handleUpload = async () => {
        if (file) {
            const formData = new FormData();
            formData.append('file', file);

            try {
                await axios.post('http://localhost:8080/image/upload', formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                });
                alert('Upload realizado com sucesso!');
                // Limpar o campo de upload após o envio
                setFile(null);
            } catch (error) {
                alert('Erro ao realizar o upload.');
                console.error(error);
            }
        } else {
            alert('Selecione um arquivo para enviar.');
        }
    };

    return (
        <div>
            <h2>Upload de Imagem</h2>
            <input type="file" onChange={handleFileChange} />
            <br /><br />
            <button onClick={handleUpload}>Enviar Imagem</button>
        </div>
    );
};

export default UploadImageComponent;
