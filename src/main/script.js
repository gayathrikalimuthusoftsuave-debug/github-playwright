fetch('report.json')
    .then(res => res.json())
    .then(data => {

        const table = document.getElementById('reportBody');
        const summary = document.getElementById('summary');

        let passCount = 0;
        let failCount = 0;

        data.forEach(test => {

            if (test.status === 'PASS') {
                passCount++;
            } else {
                failCount++;
            }

            const row = `
                <tr>
                    <td>${test.testName}</td>
                    <td style="color:${test.status === 'PASS' ? 'green' : 'red'}">
                        ${test.status}
                    </td>
                    <td>${test.message}</td>
                    <td>
                        ${test.screenshot ? `<img src="${test.screenshot}" width="100"/>` : 'N/A'}
                    </td>
                </tr>
            `;

            table.innerHTML += row;
        });

        const total = passCount + failCount;
        const passRate = ((passCount / total) * 100).toFixed(2);
        const failRate = ((failCount / total) * 100).toFixed(2);

        summary.innerHTML = `
            Total: ${total} |
            ✅ Pass: ${passCount} (${passRate}%) |
            ❌ Fail: ${failCount} (${failRate}%)
        `;
    });