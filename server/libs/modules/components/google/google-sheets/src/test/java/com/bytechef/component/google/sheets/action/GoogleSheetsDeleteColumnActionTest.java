/*
 * Copyright 2023-present ByteChef Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bytechef.component.google.sheets.action;

import static com.bytechef.component.google.sheets.constant.GoogleSheetsConstants.LABEL;
import static com.bytechef.component.google.sheets.constant.GoogleSheetsConstants.SHEET_ID;
import static com.bytechef.component.google.sheets.constant.GoogleSheetsConstants.SPREADSHEET_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import com.bytechef.component.definition.ActionContext;
import com.bytechef.component.definition.Parameters;
import com.bytechef.component.test.definition.MockParametersFactory;
import com.bytechef.google.commons.GoogleServices;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetResponse;
import com.google.api.services.sheets.v4.model.DeleteDimensionRequest;
import com.google.api.services.sheets.v4.model.DimensionRange;
import com.google.api.services.sheets.v4.model.Request;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;

/**
 * @author Marija Horvat
 */
class GoogleSheetsDeleteColumnActionTest {

    private final ArgumentCaptor<BatchUpdateSpreadsheetRequest> batchUpdateSpreadsheetRequestArgumentCaptor =
        ArgumentCaptor.forClass(BatchUpdateSpreadsheetRequest.class);
    private final BatchUpdateSpreadsheetResponse mockedBatchUpdateSpreadsheetResponse =
        mock(BatchUpdateSpreadsheetResponse.class);
    private final Sheets.Spreadsheets.BatchUpdate mockedBatchUpdate = mock(Sheets.Spreadsheets.BatchUpdate.class);
    private final ActionContext mockedContext = mock(ActionContext.class);
    private final Sheets mockedSheets = mock(Sheets.class);
    private final Sheets.Spreadsheets mockedSpreadsheets = mock(Sheets.Spreadsheets.class);
    private final Parameters parameters = MockParametersFactory.create(
        Map.of(SPREADSHEET_ID, "spreadsheetId", SHEET_ID, 123, LABEL, "B"));
    private final ArgumentCaptor<String> spreadsheetIdArgumentCaptor = ArgumentCaptor.forClass(String.class);

    @Test
    void perform() throws Exception {
        try (MockedStatic<GoogleServices> googleServicesMockedStatic = mockStatic(GoogleServices.class)) {
            googleServicesMockedStatic
                .when(() -> GoogleServices.getSheets(parameters))
                .thenReturn(mockedSheets);

            when(mockedSheets.spreadsheets())
                .thenReturn(mockedSpreadsheets);
            when(mockedSpreadsheets.batchUpdate(spreadsheetIdArgumentCaptor.capture(),
                batchUpdateSpreadsheetRequestArgumentCaptor.capture()))
                    .thenReturn(mockedBatchUpdate);
            when(mockedBatchUpdate.execute())
                .thenReturn(mockedBatchUpdateSpreadsheetResponse);

            GoogleSheetsDeleteColumnAction.perform(parameters, parameters, mockedContext);

            assertEquals("spreadsheetId", spreadsheetIdArgumentCaptor.getValue());

            BatchUpdateSpreadsheetRequest batchUpdateSpreadsheetRequest =
                batchUpdateSpreadsheetRequestArgumentCaptor.getValue();

            List<Request> requests = batchUpdateSpreadsheetRequest.getRequests();

            assertEquals(1, requests.size());

            Request request = requests.getFirst();

            DeleteDimensionRequest deleteDimensionRequest = request.getDeleteDimension();

            DimensionRange dimensionRange = deleteDimensionRequest.getRange();

            assertEquals(123, dimensionRange.getSheetId());
            assertEquals("COLUMNS", dimensionRange.getDimension());
            assertEquals(1, dimensionRange.getStartIndex());
            assertEquals(2, dimensionRange.getEndIndex());
        }
    }
}
