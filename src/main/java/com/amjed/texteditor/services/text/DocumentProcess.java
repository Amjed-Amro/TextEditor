package com.amjed.texteditor.services.text;

import com.amjed.texteditor.models.document.EfficientDocument;

public interface DocumentProcess {
    EfficientDocument processText(String text);
}
